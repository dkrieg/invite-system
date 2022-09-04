package com.invite.benefit.service;

import com.invite.benefit.domain.BenefitTierRequest;
import com.invite.benefit.mapper.BenefitTierMapper;
import com.invite.benefit.repository.BenefitTierRepository;
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
class BenefitTierDomainServiceImpl implements BenefitTierDomainService {
    BenefitTierRepository repository;
    BenefitTierMapper mapper;

    @Override
    public Collection<String> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public String create(BenefitTierRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<String> fetchById(String id) {
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
