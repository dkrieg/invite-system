package com.invite.job.domain;

import com.invite.club.domain.Club;
import com.invite.membership.domain.Membership;
import com.invite.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AdditionalRoundPermittedVariables {
    String reservationDate;
    List<Reservation> reservations;
    List<RoundsRestriction> roundsRestrictionList;
    Club serviceClub;
    Membership membership;
}
