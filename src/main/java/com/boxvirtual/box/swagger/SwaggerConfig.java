package com.boxvirtual.box.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.front.url}")
    private String swaggerFrontUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boxvirtual.box.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .host(swaggerFrontUrl);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "BOX VIRTUAL API BACK",
                "Gestion de usuarios y pacientes Bpx virtual TCLINE",
                "version 1.0.0",
                "https://developers.google.com/terms/",
                new Contact("South Code","","contacto@southcode.cl"),
                "License of API", "https://opensource.org/licenses/MIT", Collections.emptyList());
    }

}

