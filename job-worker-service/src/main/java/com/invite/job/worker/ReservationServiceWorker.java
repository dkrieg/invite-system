package com.invite.job.worker;

import com.invite.job.domain.ClubVariable;
import com.invite.job.domain.ReservationListVariable;
import com.invite.job.domain.ReservationRequestVariable;
import com.invite.job.domain.ReservationVariable;
import com.invite.job.gateway.ClubServiceGateway;
import com.invite.job.gateway.ReservationServiceGateway;
import com.invite.reservation.domain.ReservationRequest;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                        .clubId(variable.getChosenClubId())
                        .reservationDate(variable.getReservationDate())
                .build()));
    }

    @ZeebeWorker(type = "get-reservations-by-membership-id", autoComplete = true)
    public ReservationListVariable handleGetReservationsByMembershipId(@ZeebeVariable Long membershipId) {
        return new ReservationListVariable(gateway.getReservationsByMembershipId(membershipId));
    }
}
