package org.threepixeldev.saungeraadmin.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Saung Era Admin API")
                        .version("1.0.0")
                        .description("RESTful API documentation for Saung Era Admin Backend. " +
                                "This API provides endpoints for managing categories, products, and other administrative operations.")
                        .contact(new Contact()
                                .name("Saung Era Development Team")
                                .email("admin@saungera.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:9002")
                                .description("Local Development Server"),
                        new Server()
                                .url("https://api.saungera.com")
                                .description("Production Server")
                ));
    }
}
