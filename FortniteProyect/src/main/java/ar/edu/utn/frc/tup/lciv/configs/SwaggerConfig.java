package ar.edu.utn.frc.tup.lciv.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    public OpenAPI api(){
        return new OpenAPI();
    }
}
