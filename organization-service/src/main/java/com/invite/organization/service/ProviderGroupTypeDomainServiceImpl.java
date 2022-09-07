package com.invite.organization.service;

import com.invite.organization.domain.ProviderGroupType;
import com.invite.organization.domain.ProviderGroupTypeRequest;
import com.invite.organization.mapper.ProviderGroupTypeMapper;
import com.invite.organization.repository.ProviderGroupTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class ProviderGroupTypeDomainServiceImpl implements ProviderGroupTypeDomainService {
    ProviderGroupTypeRepository repository;
    ProviderGroupTypeMapper mapper;

    @Override
    public Collection<ProviderGroupType> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ProviderGroupType create(ProviderGroupTypeRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<ProviderGroupType> updateById(String id, ProviderGroupTypeRequest request) {
        return repository.findById(id)
                .map(e -> mapper.toEntity(e, request))
                .map(repository::saveAndFlush)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ProviderGroupType> fetchById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean deleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
