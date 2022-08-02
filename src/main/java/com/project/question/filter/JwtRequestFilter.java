package com.project.question.filter;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.project.question.exception.GlobalExceptionHandler;
import com.project.question.exception.UnauthenticatedException;
import com.project.question.service.JwtUserDetailsService;
import com.project.question.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	public static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private GlobalExceptionHandler jwtExceptionHandler;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(AUTHENTICATION_HEADER_NAME);

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER_TOKEN_PREFIX)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
            	LOGGER.warn("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
            	LOGGER.warn("JWT Token has expired");
            }catch (UnauthenticatedException e){
            	LOGGER.info("exception while validating the JWT token");
                try {
                	LOGGER.info("handling the exception occurred while validating the JWT token");
                    handlerExceptionResolver.resolveException(request, response, new HandlerMethod(
                    		jwtExceptionHandler, "handleUnauthenticatedException", UnauthenticatedException.class), e);
                    return;
                } catch (NoSuchMethodException ex) {
                	LOGGER.error("Error while creating HandlerMethod: ", ex);
                }
            }
        } else {
        	LOGGER.debug("JWT Token does not begin with Bearer token prefix");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        	UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            LOGGER.debug("validating the JWT token");
            Boolean validateToken = null;
            try {
            	LOGGER.info("validating the JWT token");
                validateToken = jwtTokenUtil.validateToken(jwtToken);
            }catch (UnauthenticatedException e){
            	LOGGER.info("exception while validating the JWT token");
                try {
                	LOGGER.info("handling the exception occurred while validating the JWT token");
                    handlerExceptionResolver.resolveException(request, response, new HandlerMethod(
                    		jwtExceptionHandler, "handleUnauthenticatedException", UnauthenticatedException.class), e);
                    return;
                } catch (NoSuchMethodException ex) {
                	LOGGER.error("Error while creating HandlerMethod: ", ex);
                }
            }
            if (validateToken) {
            	LOGGER.debug("validated the JWT token");

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

	/*
	 * @Override protected boolean shouldNotFilter(HttpServletRequest request)
	 * throws ServletException { return !(request.getRequestURI()!=null &&
	 * request.getRequestURI().contains("admin")); }
	 */
}
