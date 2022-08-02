package com.project.question.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.question.filter.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.project.question"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Value("${jwt.authentication-enabled}")
	private Boolean authenticationEnabled;

	/*
	 * @Autowired public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	//@Override
	/*
	 * protected void configureGlobal(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.userDetailsService(userDetailsService).passwordEncoder(new
	 * BCryptPasswordEncoder()); }
	 */

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	
	@Bean 
	  public PasswordEncoder passwordEncoder() { return new
	  BCryptPasswordEncoder(); }
	
	//@Autowired
	
	  protected void configure(AuthenticationManagerBuilder auth) throws
	  Exception {
	  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
	  ); }
	 
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
	 */
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		if(authenticationEnabled){
			httpSecurity.csrf().disable()
					.authorizeRequests()
					.antMatchers("/question/**").authenticated()
					.anyRequest().permitAll().and()
					.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}else{
			httpSecurity.csrf().disable()
					.authorizeRequests().antMatchers("/**").permitAll();
		}

	}
}