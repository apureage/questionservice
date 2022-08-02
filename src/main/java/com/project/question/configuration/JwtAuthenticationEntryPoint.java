package com.project.question.configuration;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.project.question.constants.QuestionErrorCodes;
import com.project.question.exception.GlobalExceptionHandler;
import com.project.question.exception.UnauthenticatedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    private static final long serialVersionUID = -7858869558953243875L;
    
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private GlobalExceptionHandler jwtExceptionHandler;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException){
    	LOGGER.error("Login failed. Not a valid user");
        try{
            throw new UnauthenticatedException(QuestionErrorCodes.UNAUTHENTICATED.getMessage());
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

    }
}

