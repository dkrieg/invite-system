package com.invite.address.service;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;

import java.util.Optional;

public interface AddressService {
    Address create(AddressRequest view);

    Optional<Address> fetchById(Long id);

    Optional<Address> updateById(Long id, AddressRequest view);

    boolean deleteById(Long id);
}
