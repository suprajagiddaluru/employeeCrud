package com.elliemae.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

/**
 * *
 *
 * @author supraja_giddaluru
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
  /**
   * Product api docket.
   *
   * @return the docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
        .securityContexts(
            Collections.singletonList(
                SecurityContext.builder()
                    .securityReferences(
                        Collections.singletonList(
                            SecurityReference.builder()
                                .reference("JWT")
                                .scopes(new AuthorizationScope[0])
                                .build()))
                    .build()))
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.elliemae.controller"))
        .paths(PathSelectors.regex("/rest.*"))
        .build()
      .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .globalResponseMessage(
            RequestMethod.GET,
            newArrayList(
                new ResponseMessageBuilder().code(200).message("Success ok!!").build(),
                new ResponseMessageBuilder().code(401).message("Not Authorized !!!").build(),
                new ResponseMessageBuilder().code(403).message("Forbidden !!!").build(),
                new ResponseMessageBuilder().code(404).message("Resource not found !!").build(),
                new ResponseMessageBuilder().code(500).message("Error !!").responseModel(new ModelRef("Error")).build()));
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo =
        new ApiInfo(
            "Elliemae POC ",
            "Some CRUD operations made for API.",
            "API",
            "Terms of service",
            new Contact("supraja", "www.example.com", "myeaddress@company.com"),
            "License of API",
            "API license URL",
            Collections.emptyList());
    return apiInfo;
  }

  /** * api key */
  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
      new HashSet<String>(Arrays.asList("application/json"));
}
