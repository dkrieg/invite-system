package com.invite.job.worker;

import com.invite.community.domain.Community;
import com.invite.job.domain.AdditionalRoundPermittedResult;
import com.invite.job.domain.AdditionalRoundPermittedVariables;
import com.invite.job.domain.RoundsRestriction;
import com.invite.membership.domain.Membership;
import com.invite.reservation.domain.Reservation;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.invite.job.domain.RestrictionAppliesTo.Club;
import static com.invite.job.domain.RoundsPerPeriod.Month;
import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class DetermineIfAdditionalRoundIsPermitted {

    @ZeebeWorker(type = "determine-if-additional-round-is-permitted", autoComplete = true)
    public AdditionalRoundPermittedResult handleDetermineIfAdditionalRoundIsPermitted(@ZeebeVariablesAsType AdditionalRoundPermittedVariables variables) {
        final LocalDateTime reservationDate = LocalDateTime.parse(variables.getReservationDate());
        final List<Reservation> reservations = variables.getReservations();
        final List<RoundsRestriction> roundsRestrictionList = variables.getRoundsRestrictionList();

        for (RoundsRestriction roundsRestriction : roundsRestrictionList) {
            Predicate<Reservation> monthOrYear = Month.equals(roundsRestriction.getMaxRoundsPerPeriod())
                    ? sameMonth(reservationDate.getMonthValue())
                    : sameYear(reservationDate.getYear());
            Predicate<Reservation> clubOrCommunity = Club.equals(roundsRestriction.getAppliesTo())
                    ? sameClub(variables.getServiceClub().getId())
                    : sameCommunity(variables.getMembership());
            long roundsReserved = reservations.stream()
                    .filter(clubOrCommunity)
                    .filter(monthOrYear)
                    .count();
            if (roundsReserved >= roundsRestriction.getMaxRounds()) {
                System.out.println(roundsRestriction.getDeclineReason());
                return AdditionalRoundPermittedResult.builder()
                        .isApproved(false)
                        .declineReason(roundsRestriction.getDeclineReason())
                        .build();
            }
        }
        return AdditionalRoundPermittedResult.builder()
                .isApproved(true)
                .build();
    }

    Predicate<Reservation> sameMonth(int reservationMonth) {
        return reservation -> LocalDateTime.parse(reservation.getReservationDate()).getMonthValue() == reservationMonth;
    }

    Predicate<Reservation> sameYear(int reservationYear) {
        return reservation -> LocalDateTime.parse(reservation.getReservationDate()).getYear() == reservationYear;
    }

    Predicate<Reservation> sameClub(Long serviceClubId) {
        return reservation -> reservation.getClub().getId().equals(serviceClubId);
    }

    Predicate<Reservation> sameCommunity(Membership membership) {
        final List<Long> homeClubCommunityIds = membership.getHomeClub()
                .getCommunities()
                .stream()
                .map(Community::getId)
                .collect(Collectors.toList());
        return reservation -> reservation.getClub()
                .getCommunities()
                .stream()
                .map(Community::getId)
                .anyMatch(homeClubCommunityIds::contains);
    }
}
