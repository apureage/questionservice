package com.project.question.configuration;

import java.text.MessageFormat;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class QuestionServiceConfig {

	private static final String BASE_NAME_FORMAT = "classpath:question/{0}";
	private static final String MESSAGES = "messages";
	private static final String ERROR_CODES = "errorCodes";
	
	@Bean
	public MessageSource questionMessageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MessageFormat.format(BASE_NAME_FORMAT,MESSAGES));
		messageSource.setAlwaysUseMessageFormat(true);
		return messageSource;
	}
	
	@Bean
	public MessageSource questionErrorCodesMessageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MessageFormat.format(BASE_NAME_FORMAT,ERROR_CODES));
		return messageSource;
	}

}
