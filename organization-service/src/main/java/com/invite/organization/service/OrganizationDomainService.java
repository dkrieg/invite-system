package com.invite.organization.service;

import com.invite.organization.domain.Organization;
import com.invite.organization.domain.OrganizationRequest;

import java.util.Collection;

public interface OrganizationDomainService extends DomainService<Organization, OrganizationRequest> {
    Collection<Organization> fetchAllByProviderGroup(Long providerGroupId);
}
