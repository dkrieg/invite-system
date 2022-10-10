package com.invite.club.service;

import com.invite.club.domain.ClubSegment;
import com.invite.club.domain.ClubSegmentRequest;
import com.invite.club.mapper.ClubSegmentMapper;
import com.invite.club.repository.ClubSegmentRepository;
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
class ClubSegmentDomainServiceImpl implements ClubSegmentDomainService {
    ClubSegmentRepository repository;
    ClubSegmentMapper mapper;

    @Override
    public Collection<ClubSegment> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ClubSegment create(ClubSegmentRequest request) {
        return Optional.ofNullable(request)
                .map(mapper::toEntity)
                .map(repository::saveAndFlush)
                .map(mapper::toDomain)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<ClubSegment> updateById(String id, ClubSegmentRequest request) {
        return repository.findById(id)
                .map(e -> mapper.toEntity(e, request))
                .map(repository::saveAndFlush)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ClubSegment> fetchById(String id) {
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
