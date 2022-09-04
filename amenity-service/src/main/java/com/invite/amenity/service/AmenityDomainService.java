package com.invite.amenity.service;

import com.invite.amenity.domain.Amenity;
import com.invite.amenity.domain.AmenityRequest;

import java.util.Collection;
import java.util.Optional;

public interface AmenityDomainService extends DomainService<Amenity, AmenityRequest, Long> {

    Collection<Amenity> fetchAllByOrganization(Long organizationId);
}