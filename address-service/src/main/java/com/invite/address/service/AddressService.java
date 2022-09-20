package com.invite.address.service;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.Distance;

import java.util.Collection;
import java.util.Optional;

public interface AddressService {

    Collection<Address> fetchAll();

    Address create(AddressRequest view);

    Optional<Address> fetchById(Long id);

    Optional<Address> updateById(Long id, AddressRequest view);

    boolean deleteById(Long id);

    Distance calculateDistance(Long start, Long end);
}
