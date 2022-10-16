package com.invite.job.domain;

import com.invite.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
