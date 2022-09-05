package com.invite.process.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
public class ProcessController {
    ZeebeClient client;

    @PostMapping("/start/new-organization")
    @Operation(description = "start-new-organization-process", summary = "Start New Organization Process")
    public ResponseEntity<Long> startNewOrganizationProcess() {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-organization-process")
                .latestVersion()
                .variables(Map.of("newOrganizationEventKey", "organization-123"))
                .send()
                .join()
                .getProcessInstanceKey());
    }

//    @PostMapping("/start/reservation")
//    @Operation(description = "start-reservation-process", summary = "Start Reservation Process")
//    public ResponseEntity<Boolean> startReservationProcess() {
//        return ResponseEntity.ok((Boolean) client.newCreateInstanceCommand()
//                .bpmnProcessId("reservation-process")
//                .latestVersion()
//                .withResult()
//                .send()
//                .join()
//                .getVariablesAsMap()
//                .get("approvalStatus"));
//    }

    @PostMapping("/submit/new-organization")
    public void newOrganizationSubmitted(@RequestBody Map<String, Object> variables) {
        client.newPublishMessageCommand()
                .messageName("newOrganizationSubmitted")
                .correlationKey("organization-123")
                .variables(variables)
                .send();
    }
    @PostMapping("/submit/new-amenity")
    public void newAmenitySubmitted(@RequestBody Map<String, Object> variables) {
        client.newPublishMessageCommand()
                .messageName("newAmenityForOrganizationSubmitted")
                .correlationKey("amenity-123")
                .variables(variables)
                .send();
    }
}
