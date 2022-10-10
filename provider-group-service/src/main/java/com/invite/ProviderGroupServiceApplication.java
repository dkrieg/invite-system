package com.invite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(info = @Info(
        title = "provider-group-service",
        description = "This application provides repository access to Provider Group entities."
))
@SpringBootApplication
@EnableFeignClients
public class ProviderGroupServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderGroupServiceApplication.class, args);
    }

}
