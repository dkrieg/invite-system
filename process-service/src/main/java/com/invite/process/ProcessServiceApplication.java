package com.invite.process;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
//@ZeebeDeployment(resources = {"classpath*:*.bpmn", "classpath*:*.dmn"})
@OpenAPIDefinition(info = @Info(
        title = "process-service",
        version = "1.0",
        description = "This application provides access to Process execution."
))
@Slf4j
public class ProcessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessServiceApplication.class, args);
    }
}
