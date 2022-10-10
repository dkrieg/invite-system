package com.invite.club.domain;

import com.invite.address.domain.Address;
import com.invite.amenity.domain.Amenity;
import com.invite.benefit.domain.BenefitPackage;
import com.invite.community.domain.Community;
import com.invite.line.domain.LineOfBusiness;
import com.invite.market.domain.Market;
import com.invite.provider.group.domain.ProviderGroup;
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
public class Club {
    @NotNull
    Long id;
    @NotBlank
    String name;
    String phoneNumber;
    String website;
    boolean isActive;
    boolean isOwned;
    boolean isAlliance;
    List<Market> markets;
    List<Community> communities;
    List<ProviderGroup> providerGroups;
    List<Amenity> amenities;
    List<BenefitPackage> benefitPackages;
    @NotNull
    ClubSegment segment;
    LineOfBusiness lineOfBusiness;
    @NotNull
    Address address;
}
