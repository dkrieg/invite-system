package com.invite.benefit.mapper;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.benefit.entity.BenefitEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitMapper {
    public Benefit toDomain(BenefitEntity entity) {
        return Benefit.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .description(entity.getDescription())
                .build();
    }

    public BenefitEntity toEntity(BenefitRequest request) {
        return toEntity(new BenefitEntity(), request);
    }

    public BenefitEntity toEntity(BenefitEntity entity, BenefitRequest request) {
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        return entity;
    }
}
