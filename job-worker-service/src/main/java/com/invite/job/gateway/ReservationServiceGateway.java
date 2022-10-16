package com.invite.job.gateway;

import com.invite.reservation.domain.Reservation;
import com.invite.reservation.domain.ReservationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("RESERVATION-SERVICE")
public interface ReservationServiceGateway {
    @GetMapping(path = "/by-membership-id/{id}", produces = APPLICATION_JSON_VALUE)
    List<Reservation> getReservationsByMembershipId(@PathVariable("id") Long id);

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Reservation createReservation(@RequestBody ReservationRequest request);
}
