package com.invite;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableZeebeClient
@EnableFeignClients
public class JobWorkerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobWorkerServiceApplication.class, args);
    }
}
