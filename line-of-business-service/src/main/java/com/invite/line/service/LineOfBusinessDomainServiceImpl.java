package com.invite.line.service;

import com.invite.line.domain.LineOfBusiness;
import com.invite.line.domain.LineOfBusinessRequest;
import com.invite.line.mapper.LineOfBusinessMapper;
import com.invite.line.repository.LineOfBusinessRepository;
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
class LineOfBusinessDomainServiceImpl implements LineOfBusinessDomainService {
    LineOfBusinessRepository repository;
    LineOfBusinessMapper mapper;

    @Override
    public Collection<LineOfBusiness> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public LineOfBusiness create(LineOfBusinessRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<LineOfBusiness> fetchById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<LineOfBusiness> updateById(Long id, LineOfBusinessRequest request) {
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
