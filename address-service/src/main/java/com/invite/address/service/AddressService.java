package com.invite.address.service;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.Distance;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AddressService extends DomainService<Address, AddressRequest, Long> {

    Distance calculateDistance(Long start, Long end);
}
