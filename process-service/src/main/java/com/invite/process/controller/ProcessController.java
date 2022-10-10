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

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/start")
public class ProcessController {
    ZeebeClient client;

    @PostMapping("/new-reservation/{membershipId}/{amenityId}/{reservationDate}")
    @Operation(description = "start-new-reservation-process", summary = "Start New Reservation Process")
    public ResponseEntity<Map<String, Object>> startReservationProcess(@PathVariable("membershipId") Long membershipId,
                                                                       @PathVariable("amenityId") Long amenityId,
                                                                       @PathVariable("reservationDate") LocalDateTime reservationDate) {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-reservation-process")
                .latestVersion()
                .variables(Map.of("membershipId", membershipId, "amenityId", amenityId, "reservationDate", reservationDate))
                .withResult()
                .send()
                .join()
                .getVariablesAsMap());
    }
}
