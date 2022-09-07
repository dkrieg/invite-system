package com.invite.benefit.service;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.benefit.mapper.BenefitMapper;
import com.invite.benefit.repository.BenefitRepository;
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
public class BenefitDomainServiceImpl implements BenefitDomainService {
    BenefitRepository repository;
    BenefitMapper mapper;

    @Override
    public Collection<Benefit> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Benefit> fetchAllByOrganizationId(Long organizationId) {
        return repository.findAllByOrganizationId(organizationId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Benefit create(BenefitRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<Benefit> fetchById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Benefit> updateById(Long id, BenefitRequest request) {
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
