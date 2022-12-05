package com.jasonvillar.tennisTournament.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
public class SpringFoxConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Tennis Tournament")
                .description("Simulate a Tennis Tournament - Exercise for GeoPagos")
                .version("0.0.1")
                .contact(new Contact("Jason Villar", "", "jason.villar633@gmail.com"))
                .build();
    }
}
