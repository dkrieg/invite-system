package com.invite.job.worker;

import com.invite.job.domain.ReservationListVariable;
import com.invite.job.domain.ReservationRequestVariable;
import com.invite.job.domain.ReservationVariable;
import com.invite.job.domain.RoundsReservedVariable;
import com.invite.job.gateway.ReservationServiceGateway;
import com.invite.reservation.domain.Reservation;
import com.invite.reservation.domain.ReservationRequest;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
                        .clubId(variable.getChosenClubId())
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

    @ZeebeWorker(type = "determine-rounds-reserved", autoComplete = true)
    public RoundsReservedVariable handleDetermineRoundsReserved(@ZeebeVariablesAsType ReservationListVariable variable) {
        final LocalDateTime now = LocalDate.now().atStartOfDay();
        final LocalDateTime lastYear = now.minusYears(1);
        final int currentYear = now.getYear();
        final Month currentMonth = now.getMonth();
        int roundsReservedPast12Months = 0;
        int roundsReservedThisMonth = 0;
        int roundsReservedThisYear = 0;

        //TODO: This logic is not entirely correct for calculating reservations made next year ex: January reservations made in December
        for (Reservation reservation : variable.getReservations()) {
            LocalDateTime reservationDate = LocalDateTime.parse(reservation.getReservationDate());
            if(reservationDate.isBefore(lastYear)) {
                continue;
            }
            roundsReservedPast12Months++;
            if(reservationDate.getYear() == currentYear) {
                roundsReservedThisYear++;
                if(reservationDate.getMonth().equals(currentMonth)) {
                    roundsReservedThisMonth++;
                }
            }
        }

        return RoundsReservedVariable.builder()
                .roundsReservedPast12Months(roundsReservedPast12Months)
                .roundsReservedThisMonth(roundsReservedThisMonth)
                .roundsReservedThisYear(roundsReservedThisYear)
                .build();
    }
}
