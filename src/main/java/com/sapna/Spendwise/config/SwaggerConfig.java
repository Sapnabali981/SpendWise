package com.sapna.Spendwise.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SpendWise Expense Tracker API",
                version = "1.0",
                description = "Spring Boot REST APIs for managing user expenses with pagination, sorting and reports",

                contact = @Contact(
                        name = "Sapna Bali",
                        email = "sapnabali45@gmail.com"
                ),

                license = @License(
                        name = "Open Source License"
                )
        ),

        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8080"
                ),

                @Server(
                        description = "Production Server",
                        url = "https://spendwise-txym.onrender.com"
                )
        }
)
public class SwaggerConfig {
}
