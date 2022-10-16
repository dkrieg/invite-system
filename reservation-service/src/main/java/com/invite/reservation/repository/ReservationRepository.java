package com.invite.reservation.repository;

import com.invite.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    Collection<ReservationEntity> findAllByMembershipId(Long membershipId);
}
