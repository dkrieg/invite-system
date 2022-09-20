package com.invite.address.service;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.Distance;
import com.invite.address.domain.GeoLocation;
import com.invite.address.entity.AddressEntity;
import com.invite.address.gateway.GeoLocationGateway;
import com.invite.address.mapper.AddressMapper;
import com.invite.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class AddressServiceImpl implements AddressService {
    AddressRepository repository;
    AddressMapper mapper;
    GeoLocationGateway gateway;

    @Override
    public Collection<Address> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Address create(AddressRequest addressRequest) {
        return Optional.of(addressRequest)
                .map(r -> mapper.toEntity(r, gateway.getGeoLocation(r)))
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
                .map(e -> mapper.toEntity(addressRequest, e, gateway.getGeoLocation(addressRequest)))
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

    @Override
    public Distance calculateDistance(Long startId, Long endId) {
        AddressEntity start = repository.getReferenceById(startId);
        AddressEntity end = repository.getReferenceById(endId);
        return gateway.calculateDistance(
                GeoLocation.builder()
                        .longitude(start.getGeoLocation().getLongitude())
                        .latitude(start.getGeoLocation().getLatitude())
                        .build(),
                GeoLocation.builder()
                        .longitude(end.getGeoLocation().getLongitude())
                        .latitude(end.getGeoLocation().getLatitude())
                        .build()
        );
    }
}
