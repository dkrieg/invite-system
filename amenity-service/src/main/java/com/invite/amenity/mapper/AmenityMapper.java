package com.invite.amenity.mapper;

import com.invite.amenity.domain.Amenity;
import com.invite.amenity.domain.AmenityRequest;
import com.invite.amenity.entity.AmenityEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AmenityMapper {

    public Amenity toDomain(AmenityEntity entity) {
        return Amenity.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public AmenityEntity toEntity(AmenityRequest request) {
        return toEntity(new AmenityEntity(), request);
    }

    public AmenityEntity toEntity(AmenityEntity entity, AmenityRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
