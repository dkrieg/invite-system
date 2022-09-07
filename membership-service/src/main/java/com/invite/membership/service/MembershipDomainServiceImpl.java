package com.invite.membership.service;

import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import com.invite.membership.gateway.BenefitGateway;
import com.invite.membership.gateway.MemberGateway;
import com.invite.membership.gateway.OrganizationGateway;
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
    MemberGateway memberGateway;
    BenefitGateway benefitGateway;
    OrganizationGateway organizationGateway;

    @Override
    public Collection<Membership> fetchAll() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.toDomain(e,
                        memberGateway.getMember(e.getMemberId()),
                        benefitGateway.getBenefitPackage(e.getBenefitPackageId()),
                        organizationGateway.getOrganization(e.getHomeClubId()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public Membership create(MembershipRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(e -> mapper.toDomain(e, memberGateway.getMember(e.getMemberId()),
                        benefitGateway.getBenefitPackage(e.getBenefitPackageId()),
                        organizationGateway.getOrganization(e.getHomeClubId()))
                )
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<Membership> fetchById(Long id) {
        return repository.findById(id).map(e -> mapper.toDomain(e, memberGateway.getMember(e.getMemberId()),
                benefitGateway.getBenefitPackage(e.getBenefitPackageId()),
                organizationGateway.getOrganization(e.getHomeClubId()))
        );
    }

    @Override
    public Optional<Membership> updateById(Long id, MembershipRequest request) {
        return repository.findById(id)
                .map(e -> mapper.toEntity(e, request))
                .map(repository::saveAndFlush)
                .map(e -> mapper.toDomain(e, memberGateway.getMember(e.getMemberId()),
                        benefitGateway.getBenefitPackage(e.getBenefitPackageId()),
                        organizationGateway.getOrganization(e.getHomeClubId()))
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
