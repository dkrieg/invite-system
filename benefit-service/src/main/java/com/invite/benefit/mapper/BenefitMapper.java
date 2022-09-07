package com.invite.benefit.mapper;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.benefit.entity.BenefitEntity;
import com.invite.benefit.repository.BenefitTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitMapper {
    BenefitTypeRepository repository;
    BenefitTypeMapper mapper;

    public Benefit toDomain(BenefitEntity entity) {
        return Benefit.builder()
                .id(entity.getId())
                .organizationId(entity.getOrganizationId())
                .type(mapper.toDomain(entity.getType()))
                .build();
    }

    public BenefitEntity toEntity(BenefitRequest request) {
        return toEntity(new BenefitEntity(), request);
    }

    public BenefitEntity toEntity(BenefitEntity entity, BenefitRequest request) {
        entity.setType(repository.getReferenceById(request.getType()));
        entity.setOrganizationId(request.getOrganizationId());
        return entity;
    }
}
