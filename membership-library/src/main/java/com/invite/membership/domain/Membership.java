package com.invite.membership.domain;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.member.domain.Member;
import com.invite.organization.domain.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Membership {
    @NotNull
    Long id;
    @NotBlank
    String loginId;
    @NotBlank
    String level;
    @NotNull
    Member member;
    @NotNull
    BenefitPackage benefitPackage;
    @NotNull
    Organization homeClub;
}
