package com.invite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(info = @Info(
        title = "line-of-business-service",
        description = "This application provides repository access to Line Of Business entities."
))
@SpringBootApplication
@EnableFeignClients
public class LineOfBusinessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LineOfBusinessServiceApplication.class, args);
    }

}
