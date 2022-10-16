package com.invite.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ReservationRequest {
    @NotNull
    Long amenityId;
    @NotNull
    Long memberId;
    @NotNull
    Long membershipId;
    @NotNull
    Long clubId;
    @NotNull
    String reservationDate;
}
