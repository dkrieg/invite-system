package com.invite.process;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.AuthInterface;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import io.camunda.zeebe.spring.client.properties.ZeebeClientConfigurationProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(resources = { "classpath*:*.bpmn", "classpath*:*.dmn"})
@OpenAPIDefinition(info = @Info(
        title = "process-service",
        description = "This application provides repository access to Process and Task entities."
))

public class ProcessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessServiceApplication.class, args);
    }

    @Bean
    CamundaTaskListClient taskListClient(AuthInterface auth) throws TaskListException {
        return new CamundaTaskListClient.Builder()
                .taskListUrl("http://localhost:8082")
                .shouldReturnVariables()
                .authentication(auth)
                .build();
    }

    @Bean
    AuthInterface authInterface(@Value("${zeebe.client.security.username}") String username,
                                @Value("${zeebe.client.security.password}") String password) {
        return new SimpleAuthentication(username, password);
    }
}
