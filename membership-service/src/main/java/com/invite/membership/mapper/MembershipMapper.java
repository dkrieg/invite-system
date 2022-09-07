package com.invite.membership.mapper;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.member.domain.Member;
import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import com.invite.membership.entity.MembershipEntity;
import com.invite.membership.repository.MembershipLevelRepository;
import com.invite.organization.domain.Organization;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MembershipMapper {
    MembershipLevelRepository repository;

    public Membership toDomain(MembershipEntity entity, Member member, BenefitPackage benefitPackage, Organization homeClub) {
        return Membership.builder()
                .id(entity.getId())
                .loginId(entity.getLoginId())
                .member(member)
                .benefitPackage(benefitPackage)
                .homeClub(homeClub)
                .level(entity.getLevel().getId())
                .build();
    }

    public MembershipEntity toEntity(MembershipRequest request) {
        return toEntity(new MembershipEntity(), request);
    }

    public MembershipEntity toEntity(MembershipEntity entity, MembershipRequest request) {
        entity.setLoginId(request.getLoginId());
        entity.setMemberId(request.getMemberId());
        entity.setBenefitPackageId(request.getBenefitPackageId());
        entity.setHomeClubId(request.getHomeClubId());
        entity.setLevel(repository.getReferenceById(request.getLevel()));
        return entity;
    }
}
