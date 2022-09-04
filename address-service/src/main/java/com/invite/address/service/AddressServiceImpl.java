package com.invite.address.service;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import com.invite.address.mapper.AddressMapper;
import com.invite.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class AddressServiceImpl implements AddressService {
    AddressRepository repository;
    AddressMapper mapper;

    @Override
    public Address create(AddressRequest addressRequest) {
        return Optional.of(addressRequest)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow();
    }

    @Override
    public Optional<Address> fetchById(Long addressId) {
        return repository.findById(addressId).map(mapper::toDomain);
    }

    @Override
    public Optional<Address> updateById(Long addressID, AddressRequest addressRequest) {
        return repository.findById(addressID)
                .map(c -> mapper.toEntity(addressRequest, c))
                .map(repository::saveAndFlush)
                .map(mapper::toDomain);
    }

    @Override
    public boolean deleteById(Long memberID) {
        if (repository.existsById(memberID)) {
            repository.deleteById(memberID);
            return true;
        } else {
            return false;
        }
    }
}
