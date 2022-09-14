package com.invite.organization.domain;

import com.invite.address.domain.AddressRequest;
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
public class OrganizationRequest {
    @NotBlank
    String name;
    Long communityId;
    Long providerGroupId;
    @NotBlank
    String segment;
    @NotNull
    AddressRequest address;
}
