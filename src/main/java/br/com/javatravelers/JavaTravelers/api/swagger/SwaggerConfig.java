package br.com.javatravelers.JavaTravelers.api.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api() {
		ParameterBuilder paramBuilder = new ParameterBuilder();
		List<Parameter> params = new ArrayList<>();
		paramBuilder.name("Authorization").modelRef(new ModelRef("string"))
		.parameterType("header")
		.required(false)
		.build();
		params.add(paramBuilder.build());
		
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		 
		docket
		.globalOperationParameters(params)
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.javatravelers"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build())
		.consumes(new HashSet<String>(Arrays.asList("application/json")))
		.produces(new HashSet<String>(Arrays.asList("application/json")));
		
		return docket;
    }
	private ApiInfoBuilder informacoesApi() {
		 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("javaTravelers REST API");
		apiInfoBuilder.description("API Rest para gerenciamento de Passagens");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("https://github.com/javaTravelers/backend\"");
		apiInfoBuilder.license("MIT License");
		apiInfoBuilder.licenseUrl("https://github.com/javaTravelers/backend");
		apiInfoBuilder.contact(this.contato());
		
		return apiInfoBuilder;
 
	}
	private Contact contato() {
		Contact contact = new Contact(
				"@javaTravelers - 2020",
				"https://github.com/javaTravelers/backend",
				"");
		 
		 return contact;
	}
    
    
}  
    
