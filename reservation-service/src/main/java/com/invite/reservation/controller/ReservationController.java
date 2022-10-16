package com.invite.reservation.controller;

import com.invite.reservation.domain.Reservation;
import com.invite.reservation.domain.ReservationRequest;
import com.invite.reservation.service.ReservationDomainService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
public class ReservationController {
    ReservationDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-reservations", summary = "Get All Reservations")
    Collection<Reservation> getReservations() {
        return service.fetchAll();
    }

    @GetMapping(path = "/by-membership-id/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-reservations-by-membership-id", summary = "Get All Reservations By Membership ID")
    Collection<Reservation> getReservationsByMembershipId(@PathVariable("id") Long id) {
        return service.fetchAllByMembershipId(id);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-reservation", summary = "Create New Reservation")
    ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request) {
        Reservation saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-reservation", summary = "Get Reservation By ID")
    ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-reservation", summary = "Update Reservation By ID")
    ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody ReservationRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-reservation", summary = "Delete Reservation By ID")
    ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
