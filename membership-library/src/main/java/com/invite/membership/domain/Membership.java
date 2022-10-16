package com.invite.membership.domain;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.member.domain.Member;
import com.invite.club.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

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
    String level;
    @NotEmpty
    List<Member> members;
    @NotNull
    BenefitPackage benefitPackage;
    @NotNull
    Club homeClub;
}
