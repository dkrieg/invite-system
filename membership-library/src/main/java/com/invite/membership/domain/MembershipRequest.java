package com.invite.membership.domain;

import com.invite.benefit.domain.BenefitPackage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class MembershipRequest {
    @NotBlank
    String loginId;
    @NotBlank
    String level;
    @NotNull
    Long memberId;
    @NotNull
    List<Long> benefitPackageIds;
    @NotNull
    Long homeClubId;
}
