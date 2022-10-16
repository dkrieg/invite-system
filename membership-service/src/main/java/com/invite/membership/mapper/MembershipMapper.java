package com.invite.membership.mapper;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.member.domain.Member;
import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import com.invite.membership.entity.MembershipBenefitPackageEntity;
import com.invite.membership.entity.MembershipEntity;
import com.invite.membership.entity.MembershipMemberEntity;
import com.invite.membership.repository.MembershipBenefitPackageRepository;
import com.invite.membership.repository.MembershipLevelRepository;
import com.invite.club.domain.Club;
import com.invite.membership.repository.MembershipMemberRepository;
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
    MembershipMemberRepository membershipMemberRepository;

    public Membership toDomain(MembershipEntity entity, List<Member> members, BenefitPackage benefitPackage, Club homeClub) {
        return Membership.builder()
                .id(entity.getId())
                .members(members)
                .benefitPackage(benefitPackage)
                .homeClub(homeClub)
                .level(entity.getLevel().getId())
                .build();
    }

    public MembershipEntity toEntity(MembershipRequest request) {
        return toEntity(new MembershipEntity(), request);
    }

    public MembershipEntity toEntity(MembershipEntity entity, MembershipRequest request) {
        entity.setMembers(request.getMemberIds()
                .stream()
                .map(id -> membershipMemberRepository.findById(id).orElseGet(() -> new MembershipMemberEntity(id)))
                .collect(Collectors.toSet()));
        entity.setBenefitPackage(Optional.ofNullable(request.getBenefitPackageId())
                .map(id -> membershipBenefitPackageRepository.findById(id).orElseGet(() -> new MembershipBenefitPackageEntity(id)))
                .orElse(null));
        entity.setHomeClubId(request.getHomeClubId());
        entity.setLevel(membershipLevelRepository.getReferenceById(request.getLevel()));
        return entity;
    }
}
