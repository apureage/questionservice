package com.project.question.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger api's configure for question services
 * @author apurba
 *
 */
@Configuration
@EnableSwagger2
public class QuestionSwaggerConfig {     
	
	private static final String BEARER_AUTH = "Bearer";
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(
			"com.project.question.controller")).paths(PathSelectors.any()).build().apiInfo(apiInfo())
			.securitySchemes(securitySchemes()).securityContexts(Arrays.asList(securityContext()));
	}

	private List<SecurityScheme> securitySchemes() {
		return Arrays.asList( new ApiKey(BEARER_AUTH, "Authorization", "header"));
	}
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(bearerAuthReference())).build();
	}

	private SecurityReference bearerAuthReference() {
		return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("Question service API")
				.description("Question API reference for developers")
				.termsOfServiceUrl("Terms of service")
				.contact(new Contact("Apurba", "", "sprinboot@java.com")).license("Question License")
				.licenseUrl("Free License").version("1.0").build();
	}
}