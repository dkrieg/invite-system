package com.invite.benefit.mapper;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.benefit.entity.BenefitEntity;
import com.invite.benefit.repository.BenefitTierRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitMapper {
    BenefitTierRepository repository;

    public Benefit toDomain(BenefitEntity entity) {
        return Benefit.builder()
                .id(entity.getId())
                .amenityId(entity.getAmenityId())
                .name(entity.getName())
                .tier(entity.getTier().getName())
                .build();
    }

    public BenefitEntity toEntity(BenefitRequest request) {
        return toEntity(new BenefitEntity(), request);
    }

    public BenefitEntity toEntity(BenefitEntity entity, BenefitRequest request) {
        entity.setName(request.getName());
        entity.setAmenityId(request.getAmenityId());
        entity.setTier(repository.getReferenceById(request.getTier()));
        return entity;
    }
}
