package com.invite.amenity.mapper;

import com.invite.amenity.domain.Amenity;
import com.invite.amenity.domain.AmenityRequest;
import com.invite.amenity.entity.AmenityEntity;
import com.invite.amenity.repository.AmenityTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AmenityMapper {
    AmenityTypeRepository repository;

    public Amenity toDomain(AmenityEntity entity) {
        return Amenity.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType().getName())
                .organizationId(entity.getOrganizationId())
                .build();
    }

    public AmenityEntity toEntity(AmenityRequest request) {
        return toEntity(new AmenityEntity(), request);
    }

    public AmenityEntity toEntity(AmenityEntity entity, AmenityRequest request) {
        entity.setName(request.getName());
        entity.setOrganizationId(request.getOrganizationId());
        entity.setType(repository.getReferenceById(request.getType()));
        return entity;
    }
}
