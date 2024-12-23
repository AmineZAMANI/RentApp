package com.infra.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String API_KEY = "apiKey";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(API_KEY,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("X-API-KEY")))
                .addSecurityItem(new SecurityRequirement().addList(API_KEY))
                .info(new Info()
                        .title("Car Rental API")
                        .version("1.0")
                        .description("API documentation for the Car Rental application.")
                        .contact(new Contact()
                                .name("Car Rental Support")
                                .email("support@carrental.com")
                                .url("https://carrental.com")));
    }
}
