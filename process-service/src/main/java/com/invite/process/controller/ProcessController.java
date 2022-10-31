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

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Objects.*;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/start")
public class ProcessController {
    ZeebeClient client;
    static Set<String> varNames = Set.of("reservationIsApproved", "reservationDeclineReason");

    @PostMapping("/new-reservation/{memberId}/{amenityId}/{serviceClubId}/{reservationDate}")
    @Operation(description = "start-new-reservation-process", summary = "Start New Reservation Process")
    public ResponseEntity<Map<String, Object>> startReservationProcess(@PathVariable("memberId") Long memberId,
                                                                       @PathVariable("amenityId") Long amenityId,
                                                                       @PathVariable("serviceClubId") Long serviceClubId,
                                                                       @PathVariable("reservationDate") String reservationDate) {
        return ResponseEntity.ok(client.newCreateInstanceCommand()
                .bpmnProcessId("new-reservation-process")
                .latestVersion()
                .variables(Map.of(
                        "memberId", memberId,
                        "amenityId", amenityId,
                        "serviceClubId", serviceClubId,
                        "reservationDate", reservationDate))
                .withResult()
                .requestTimeout(Duration.of(2, ChronoUnit.MINUTES))
                .send()
                .join(2, TimeUnit.MINUTES)
                .getVariablesAsMap()
                .entrySet()
                .stream()
                .filter(e -> varNames.contains(e.getKey()) && nonNull(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
