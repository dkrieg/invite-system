package com.invite.membership.mapper;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.member.domain.Member;
import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import com.invite.membership.entity.MembershipBenefitPackageEntity;
import com.invite.membership.entity.MembershipEntity;
import com.invite.membership.repository.MembershipBenefitPackageRepository;
import com.invite.membership.repository.MembershipLevelRepository;
import com.invite.club.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MembershipMapper {
    MembershipLevelRepository membershipLevelRepository;
    MembershipBenefitPackageRepository membershipBenefitPackageRepository;

    public Membership toDomain(MembershipEntity entity, Member member, List<BenefitPackage> benefitPackages, Club homeClub) {
        return Membership.builder()
                .id(entity.getId())
                .loginId(entity.getLoginId())
                .member(member)
                .benefitPackages(benefitPackages)
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
        entity.setBenefitPackages(Optional.ofNullable(request.getBenefitPackageIds())
                .map(l -> l
                        .stream()
                        .map(id -> membershipBenefitPackageRepository.findById(id).orElseGet(() -> new MembershipBenefitPackageEntity(id)))
                        .collect(Collectors.toSet()))
                .orElseGet(Set::of));
        entity.setHomeClubId(request.getHomeClubId());
        entity.setLevel(membershipLevelRepository.getReferenceById(request.getLevel()));
        return entity;
    }
}
