package com.invite.club.domain;

import com.invite.address.domain.AddressRequest;
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
public class ClubRequest {
    @NotBlank
    String name;
    String phoneNumber;
    String website;
    boolean isActive;
    boolean isOwned;
    boolean isAlliance;
    List<Long> marketIds;
    List<Long> communityIds;
    List<Long> providerGroupIds;
    List<Long> amenityIds;
    List<Long> benefitPackageIds;
    @NotBlank
    String segment;
    Long lineOfBusinessId;
    @NotNull
    AddressRequest address;
}
