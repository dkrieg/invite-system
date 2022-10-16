package com.invite.membership.service;

import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import com.invite.membership.entity.MembershipMemberEntity;
import com.invite.membership.gateway.BenefitServiceGateway;
import com.invite.membership.gateway.ClubServiceGateway;
import com.invite.membership.gateway.MemberServiceGateway;
import com.invite.membership.mapper.MembershipMapper;
import com.invite.membership.repository.MembershipRepository;
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
class MembershipDomainServiceImpl implements MembershipDomainService {
    MembershipRepository repository;
    MembershipMapper mapper;
    MemberServiceGateway memberServiceGateway;
    BenefitServiceGateway benefitServiceGateway;
    ClubServiceGateway clubServiceGateway;

    @Override
    public Collection<Membership> fetchAll() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.toDomain(e,
                        e.getMembers()
                                .stream()
                                .map(MembershipMemberEntity::getId)
                                .map(memberServiceGateway::getMember)
                                .collect(Collectors.toList()),
                        benefitServiceGateway.getBenefitPackage(e.getBenefitPackage().getId()),
                        clubServiceGateway.getClub(e.getHomeClubId()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Membership> fetchByMembersId(Long memberId) {
        return repository.findByMembersId(memberId).map(e -> mapper.toDomain(e, e.getMembers()
                        .stream()
                        .map(MembershipMemberEntity::getId)
                        .map(memberServiceGateway::getMember)
                        .collect(Collectors.toList()),
                benefitServiceGateway.getBenefitPackage(e.getBenefitPackage().getId()),
                clubServiceGateway.getClub(e.getHomeClubId()))
        );
    }

    @Override
    public Membership create(MembershipRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(e -> mapper.toDomain(e,
                        e.getMembers()
                                .stream()
                                .map(MembershipMemberEntity::getId)
                                .map(memberServiceGateway::getMember)
                                .collect(Collectors.toList()),
                        benefitServiceGateway.getBenefitPackage(e.getBenefitPackage().getId()),
                        clubServiceGateway.getClub(e.getHomeClubId()))
                )
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<Membership> fetchById(Long id) {
        return repository.findById(id).map(e -> mapper.toDomain(e, e.getMembers()
                        .stream()
                        .map(MembershipMemberEntity::getId)
                        .map(memberServiceGateway::getMember)
                        .collect(Collectors.toList()),
                benefitServiceGateway.getBenefitPackage(e.getBenefitPackage().getId()),
                clubServiceGateway.getClub(e.getHomeClubId()))
        );
    }

    @Override
    public Optional<Membership> updateById(Long id, MembershipRequest request) {
        return repository.findById(id)
                .map(e -> mapper.toEntity(e, request))
                .map(repository::saveAndFlush)
                .map(e -> mapper.toDomain(e, e.getMembers()
                                .stream()
                                .map(MembershipMemberEntity::getId)
                                .map(memberServiceGateway::getMember)
                                .collect(Collectors.toList()),
                        benefitServiceGateway.getBenefitPackage(e.getBenefitPackage().getId()),
                        clubServiceGateway.getClub(e.getHomeClubId()))
                );
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
