package com.invite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "amenity-service",
        description = "This application provides repository access to Amenity and Amenity Restriction entities."
))
@SpringBootApplication
public class AmenityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmenityServiceApplication.class, args);
    }

}
