package com.aliza.alizaShop.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    private fun createAPIKeyScheme(): SecurityScheme {
        return SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")

    }

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI().addSecurityItem(
            SecurityRequirement().addList
                ("Bearer Authentication")
        )
            .components(Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
            .info(
                Info().title("Aliza Shop API")
                    .description("Some custom description of API.")
                    .version("1.0").contact(
                        Contact().name("ALIZA")
                            .email("www.aliza.com").url("alizadev.sh@gmail.com")
                    )
                    .license(
                        License().name("License of API")
                            .url("API license URL")
                    )
            )
    }
}