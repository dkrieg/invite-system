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
    String level;
    @NotNull
    List<Long> memberIds;
    @NotNull
    Long benefitPackageId;
    @NotNull
    Long homeClubId;
}
