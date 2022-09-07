package com.invite.membership.service;

import com.invite.membership.domain.MembershipLevelRequest;
import com.invite.membership.entity.MembershipLevelEntity;
import com.invite.membership.repository.MembershipLevelRepository;
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
class MembershipLevelDomainServiceImpl implements MembershipLevelDomainService {
    MembershipLevelRepository repository;

    @Override
    public Collection<String> fetchAll() {
        return repository.findAll()
                .stream()
                .map(MembershipLevelEntity::getId)
                .collect(Collectors.toList());
    }

    @Override
    public String create(MembershipLevelRequest request) {
        return Optional.ofNullable(request.getId())
                .map(MembershipLevelEntity::new)
                .map(repository::saveAndFlush)
                .map(MembershipLevelEntity::getId)
                .orElseThrow(PersistenceException::new);
    }

    @Override
    public Optional<String> fetchById(String id) {
        return repository.findById(id).map(MembershipLevelEntity::getId);
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
