package com.invite.process.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/submit")
public class MessageController {
    ZeebeClient client;

    @PostMapping("/new-organization")
    public void newOrganizationSubmitted(@RequestBody Map<String, Object> variables) {
        client.newPublishMessageCommand()
                .messageName("newOrganizationSubmitted")
                .correlationKey("organization-123")
                .variables(variables)
                .send();
    }

    @PostMapping("/new-amenity")
    public void newAmenitySubmitted(@RequestBody Map<String, Object> variables) {
        client.newPublishMessageCommand()
                .messageName("newAmenityForOrganizationSubmitted")
                .correlationKey("amenity-123")
                .variables(variables)
                .send();
    }

    @PostMapping("/new-benefit")
    public void newBenefitSubmitted(@RequestBody Map<String, Object> variables) {
        client.newPublishMessageCommand()
                .messageName("newBenefitForOrganizationSubmitted")
                .correlationKey("benefit-123")
                .variables(variables)
                .send();
    }

    @PostMapping("/new-benefit-package")
    public void newBenefitPackageSubmitted(@RequestBody Map<String, Object> variables) {
        client.newPublishMessageCommand()
                .messageName("newBenefitPackageForOrganizationSubmitted")
                .correlationKey("benefit-package-123")
                .variables(variables)
                .send();
    }
}
