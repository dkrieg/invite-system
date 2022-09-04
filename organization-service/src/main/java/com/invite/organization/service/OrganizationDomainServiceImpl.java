package com.invite.organization.service;

import com.invite.address.domain.Address;
import com.invite.organization.domain.Organization;
import com.invite.organization.domain.OrganizationRequest;
import com.invite.organization.entity.OrganizationEntity;
import com.invite.organization.gateway.AddressGateway;
import com.invite.organization.mapper.OrganizationMapper;
import com.invite.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class OrganizationDomainServiceImpl implements OrganizationDomainService {
    OrganizationRepository repository;
    OrganizationMapper mapper;
    AddressGateway gateway;
    @Override
    public Collection<Organization> fetchAll() {
        return repository.findAll()
                .stream()
                .map(entity -> mapper.toDomain(entity, gateway.getAddress(entity.getAddressId())))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Organization> fetchAllByProviderGroup(Long providerGroupId) {
        return repository.findAllByProviderGroup(providerGroupId)
                .stream()
                .map(entity -> mapper.toDomain(entity, gateway.getAddress(entity.getAddressId())))
                .collect(Collectors.toList());
    }

    @Override
    public Organization create(OrganizationRequest request) {
        return Optional.ofNullable(request)
                .map(r -> {
                    Address address = gateway.createAddress(r.getAddress());
                    OrganizationEntity entity = mapper.toEntity(r, address);
                    return Map.entry(entity, address);
                })
                .map(e -> {
                    Address address = e.getValue();
                    try {
                        OrganizationEntity entity = repository.saveAndFlush(e.getKey());
                        return Map.entry(entity, address);
                    } catch (PersistenceException ex) {
                        gateway.deleteAddress(address.getId());
                        throw ex;
                    }
                })
                .map(e -> mapper.toDomain(e.getKey(), e.getValue()))
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<Organization> fetchById(Long id) {
        return repository.findById(id).map(e -> mapper.toDomain(e, gateway.getAddress(e.getAddressId())));
    }

    @Override
    public Optional<Organization> updateById(Long id, OrganizationRequest request) {
        return repository.findById(id)
                .map(e -> {
                    Address address = gateway.updateAddress(e.getAddressId(), request.getAddress());
                    OrganizationEntity entity = mapper.toEntity(e, request, address);
                    return Map.entry(entity, address);
                })
                .map(e -> Map.entry(repository.saveAndFlush(e.getKey()), e.getValue()))
                .map(e -> mapper.toDomain(e.getKey(), e.getValue()));
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            gateway.deleteAddress(repository.getReferenceById(id).getAddressId());
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
