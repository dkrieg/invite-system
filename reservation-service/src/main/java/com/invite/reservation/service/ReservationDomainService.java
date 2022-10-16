package com.invite.reservation.service;

import com.invite.domain.service.DomainService;
import com.invite.reservation.domain.Reservation;
import com.invite.reservation.domain.ReservationRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface ReservationDomainService extends DomainService<Reservation, ReservationRequest, Long> {

    Collection<Reservation> fetchAllByMembershipId(Long membershipId);
}
