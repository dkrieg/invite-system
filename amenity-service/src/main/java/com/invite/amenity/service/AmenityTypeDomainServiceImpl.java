package com.invite.amenity.service;

import com.invite.amenity.domain.AmenityTypeRequest;
import com.invite.amenity.entity.AmenityTypeEntity;
import com.invite.amenity.repository.AmenityTypeRepository;
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
public class AmenityTypeDomainServiceImpl implements AmenityTypeDomainService {
    AmenityTypeRepository repository;

    @Override
    public Collection<String> fetchAll() {
        return repository.findAll()
                .stream()
                .map(AmenityTypeEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String create(AmenityTypeRequest request) {
        return repository.saveAndFlush(new AmenityTypeEntity(request.getName())).getName();
    }

    @Override
    public Optional<String> fetchById(String name) {
        return repository.findById(name).map(AmenityTypeEntity::getName);
    }

    @Override
    public boolean deleteById(String name) {
        if (repository.existsById(name)) {
            repository.deleteById(name);
            return true;
        } else {
            return false;
        }
    }
}
