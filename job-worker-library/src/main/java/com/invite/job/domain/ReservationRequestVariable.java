package com.invite.job.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ReservationRequestVariable {
    Long amenityId;
    Long memberId;
    Long membershipId;
    Long chosenClubId;
    String reservationDate;
}
