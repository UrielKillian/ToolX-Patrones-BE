package com.jossticeteam.toolx.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "toolxOpenApi")
    public OpenAPI toolxOpenApi(){
        return new OpenAPI()
                .info(new Info()
                .title("ToolX Application API")
                .description("ToolX API implemented with Spring Boot RESTful service and document using springdoc-openapi and OpenAPI 3.0"));
    }
}
