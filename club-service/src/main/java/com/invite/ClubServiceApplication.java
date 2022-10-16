package com.invite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(info = @Info(
        title = "club-service",
        description = "This application provides repository access to Club entities."
))
@SpringBootApplication
@EnableFeignClients
public class ClubServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubServiceApplication.class, args);
    }

}
