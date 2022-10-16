package com.invite.reservation.service;

import com.invite.reservation.domain.Reservation;
import com.invite.reservation.domain.ReservationRequest;
import com.invite.reservation.gateway.AmenityServiceGateway;
import com.invite.reservation.gateway.ClubServiceGateway;
import com.invite.reservation.gateway.MemberServiceGateway;
import com.invite.reservation.gateway.MembershipServiceGateway;
import com.invite.reservation.mapper.ReservationMapper;
import com.invite.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class ReservationDomainServiceImpl implements ReservationDomainService {
    ReservationRepository repository;
    ReservationMapper mapper;
    MembershipServiceGateway membershipServiceGateway;
    MemberServiceGateway memberServiceGateway;
    AmenityServiceGateway amenityServiceGateway;
    ClubServiceGateway clubServiceGateway;

    @Override
    public Collection<Reservation> fetchAll() {
        return repository.findAll()
                .stream()
                .map(entity -> mapper.toDomain(entity,
                        memberServiceGateway.getMember(entity.getMemberId()),
                        membershipServiceGateway.getMembership(entity.getMembershipId()),
                        amenityServiceGateway.getAmenity(entity.getAmenityId()),
                        clubServiceGateway.getClub(entity.getClubId())))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation create(ReservationRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(entity -> mapper.toDomain(entity,
                                memberServiceGateway.getMember(entity.getMemberId()),
                                membershipServiceGateway.getMembership(entity.getMembershipId()),
                                amenityServiceGateway.getAmenity(entity.getAmenityId()),
                                clubServiceGateway.getClub(entity.getClubId())))
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Collection<Reservation> fetchAllByMembershipId(Long membershipId) {
        return repository.findAllByMembershipId(membershipId)
                .stream()
                .map(entity -> mapper.toDomain(entity,
                                memberServiceGateway.getMember(entity.getMemberId()),
                                membershipServiceGateway.getMembership(entity.getMembershipId()),
                                amenityServiceGateway.getAmenity(entity.getAmenityId()),
                                clubServiceGateway.getClub(entity.getClubId())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> fetchById(Long id) {
        return repository.findById(id).map(entity -> mapper.toDomain(entity,
                memberServiceGateway.getMember(entity.getMemberId()),
                membershipServiceGateway.getMembership(entity.getMembershipId()),
                amenityServiceGateway.getAmenity(entity.getAmenityId()),
                clubServiceGateway.getClub(entity.getClubId())));
    }

    @Override
    public Optional<Reservation> updateById(Long id, ReservationRequest request) {
        return repository.findById(id)
                .map(e -> mapper.toEntity(e, request))
                .map(repository::saveAndFlush)
                .map(entity -> mapper.toDomain(entity,
                        memberServiceGateway.getMember(entity.getMemberId()),
                        membershipServiceGateway.getMembership(entity.getMembershipId()),
                        amenityServiceGateway.getAmenity(entity.getAmenityId()),
                        clubServiceGateway.getClub(entity.getClubId())));
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
