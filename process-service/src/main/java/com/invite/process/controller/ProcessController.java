package com.invite.process.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/start")
public class ProcessController {
    ZeebeClient client;

    @PostMapping("/new-organization")
    @Operation(description = "start-new-organization-process", summary = "Start New Organization Process")
    public ResponseEntity<Long> startNewOrganizationProcess() {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-organization-process")
                .latestVersion()
                .send()
                .join()
                .getProcessInstanceKey());
    }

    @PostMapping("/new-member")
    @Operation(description = "start-new-member-process", summary = "Start New Member Process")
    public ResponseEntity<Long> startNewMemberProcess() {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-member-process")
                .latestVersion()
                .send()
                .join()
                .getProcessInstanceKey());
    }

    @PostMapping("/new-membership")
    @Operation(description = "start-new-membership-process", summary = "Start New Membership Process")
    public ResponseEntity<Long> startNewMembershipProcess() {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-membership-process")
                .latestVersion()
                .send()
                .join()
                .getProcessInstanceKey());
    }

    @PostMapping("/new-reservation/{membershipId}/{amenityId}")
    @Operation(description = "start-new-reservation-process", summary = "Start New Reservation Process")
    public ResponseEntity<Map<String, Object>> startReservationProcess(@PathVariable("membershipId") Long membershipId, @PathVariable("amenityId") Long amenityId) {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-reservation-process")
                .latestVersion()
                .variables(Map.of("membershipId", membershipId, "amenityId", amenityId))
                .withResult()
                .send()
                .join()
                .getVariablesAsMap());
    }
}
