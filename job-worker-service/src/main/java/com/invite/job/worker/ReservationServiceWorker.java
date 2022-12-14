package com.invite.job.worker;

import com.invite.job.domain.ReservationListVariable;
import com.invite.job.domain.ReservationRequestVariable;
import com.invite.job.domain.ReservationVariable;
import com.invite.job.gateway.ReservationServiceGateway;
import com.invite.reservation.domain.ReservationRequest;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ReservationServiceWorker {
    ReservationServiceGateway gateway;

    @ZeebeWorker(type = "create-reservation", autoComplete = true)
    public ReservationVariable handleCreateReservation(@ZeebeVariablesAsType ReservationRequestVariable variable) {
        return new ReservationVariable(gateway.createReservation(ReservationRequest.builder()
                .memberId(variable.getMemberId())
                .membershipId(variable.getMembershipId())
                .amenityId(variable.getAmenityId())
                .clubId(variable.getServiceClubId())
                .reservationDate(variable.getReservationDate())
                .build()));
    }

    @ZeebeWorker(type = "get-golf-reservations-by-membership-id", autoComplete = true)
    public ReservationListVariable handleGetGolfReservationsByMembershipId(@ZeebeVariable Integer membershipId) {
        return new ReservationListVariable(gateway.getReservationsByMembershipId(membershipId.longValue())
                .stream()
                .filter(r -> r.getAmenity().getName().equals("GOLF"))
                .collect(Collectors.toList()));
    }
}
