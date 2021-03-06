package com.nnk.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * Configuration to have the Swagger documentation for APIs
 * </p>
 * 
 * @author nicolas
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * <p>Creation of the docket for documentation</p>
	 * @return Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.nnk.springboot.controller")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	/**
	 * <p>Main info of the documentation</p>
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API nnk").version("1.0.0").build();
	}
}