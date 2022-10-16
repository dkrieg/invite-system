package com.invite.reservation.domain;

import com.invite.amenity.domain.Amenity;
import com.invite.club.domain.Club;
import com.invite.member.domain.Member;
import com.invite.membership.domain.Membership;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Reservation {
    @NotNull
    Long id;
    @NotNull
    Amenity amenity;
    @NotNull
    Member member;
    @NotNull
    Membership membership;
    @NotNull
    Club club;
    @NotNull
    String reservationDate;
}
