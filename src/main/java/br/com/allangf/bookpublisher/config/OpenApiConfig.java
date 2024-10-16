package br.com.allangf.bookpublisher.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Book Publisher",
        version = "1.0.0",
        description = "Inventory control of a publisher",
        contact = @Contact(name = "Allan Garcia Ferreira",
                email = "allan201gf@gmail.com")))
public class OpenApiConfig {
}