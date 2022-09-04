package com.invite.organization.service;

import com.invite.organization.domain.ProviderGroup;
import com.invite.organization.domain.ProviderGroupRequest;
import com.invite.organization.mapper.ProviderGroupMapper;
import com.invite.organization.repository.ProviderGroupRepository;
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
class ProviderGroupDomainServiceImpl implements ProviderGroupDomainService {
    ProviderGroupRepository repository;
    ProviderGroupMapper mapper;

    @Override
    public Collection<ProviderGroup> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ProviderGroup create(ProviderGroupRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<ProviderGroup> fetchById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<ProviderGroup> updateById(Long id, ProviderGroupRequest request) {
        return repository.findById(id)
                .map(e -> mapper.toEntity(e, request))
                .map(repository::saveAndFlush)
                .map(mapper::toDomain);
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
