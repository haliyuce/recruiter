package com.heavenhr.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RecruiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruiterApplication.class, args);
	}
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
				.build().apiInfo(generateApiInfo());
	}

	private ApiInfo generateApiInfo() {
		
		return new ApiInfo("HeavenHR Server Applicant Test Service",
				"This service is to check the technology knowledge of a server applicant for heavenhr.",
				"Version 1.0 - mw", "urn:tos", "admin@heavenhr.com", "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0");
	}
	
	@Bean(name = "OBJECT_MAPPER_BEAN")
	public ObjectMapper jsonObjectMapper() {
	    return Jackson2ObjectMapperBuilder.json()
	            .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
	            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
	            .modules(new JavaTimeModule())
	            .build();
	}

}
