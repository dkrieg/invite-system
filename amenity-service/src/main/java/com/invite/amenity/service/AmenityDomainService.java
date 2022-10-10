package com.invite.amenity.service;

import com.invite.amenity.domain.Amenity;
import com.invite.amenity.domain.AmenityRequest;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public interface AmenityDomainService extends DomainService<Amenity, AmenityRequest, Long> {

    Collection<Amenity> fetchAllById(List<Long> ids);
}
