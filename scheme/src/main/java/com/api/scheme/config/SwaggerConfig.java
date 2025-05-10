package com.api.scheme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

     @Bean
    public OpenAPI schemeApiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("Scheme Management API")
                .description("API documentation for managing Schemes and Scheme Particulars")
                .version("1.0"));
    }
}
