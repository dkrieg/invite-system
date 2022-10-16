package com.invite.reservation.mapper;

import com.invite.amenity.domain.Amenity;
import com.invite.club.domain.Club;
import com.invite.member.domain.Member;
import com.invite.membership.domain.Membership;
import com.invite.reservation.domain.Reservation;
import com.invite.reservation.domain.ReservationRequest;
import com.invite.reservation.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ReservationMapper {

    public Reservation toDomain(ReservationEntity entity, Member member, Membership membership, Amenity amenity, Club club) {
        return Reservation.builder()
                .id(entity.getId())
                .member(member)
                .membership(membership)
                .amenity(amenity)
                .club(club)
                .reservationDate(entity.getReservationDate().toString())
                .build();
    }

    public ReservationEntity toEntity(ReservationRequest request) {
        return toEntity(new ReservationEntity(), request);
    }

    public ReservationEntity toEntity(ReservationEntity entity, ReservationRequest request) {
        entity.setMemberId(request.getMemberId());
        entity.setMembershipId(request.getMembershipId());
        entity.setAmenityId(request.getAmenityId());
        entity.setClubId(request.getClubId());
        entity.setReservationDate(LocalDateTime.parse(request.getReservationDate()));
        return entity;
    }
}
