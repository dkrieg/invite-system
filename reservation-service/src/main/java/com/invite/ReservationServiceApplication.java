package com.invite;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(info = @Info(
        title = "reservation-service",
        version = "1.0",
        description = "This application provides repository access to Reservation entities."
))
@SpringBootApplication
@EnableFeignClients
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

}
