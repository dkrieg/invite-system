package com.invite.organization.domain;

import com.invite.address.domain.Address;
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
public class Organization {
    @NotNull
    Long id;
    @NotBlank
    String name;
    Community community;
    Market market;
    ProviderGroup providerGroup;
    @NotNull
    OrganizationSegment segment;
    @NotNull
    Address address;
}
