package com.invite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "benefit-service",
        version = "1.0",
        description = "This application provides repository access to Benefit nd Benefit Package entities."
))
@SpringBootApplication
public class BenefitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenefitServiceApplication.class, args);
    }

}
