package com.invite.benefit.mapper;

import com.invite.benefit.domain.BenefitType;
import com.invite.benefit.domain.BenefitTypeRequest;
import com.invite.benefit.entity.BenefitTypeEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitTypeMapper {

    public BenefitType toDomain(BenefitTypeEntity entity) {
        return BenefitType.builder()
                .code(entity.getCode())
                .description(entity.getDescription())
                .build();
    }

    public BenefitTypeEntity toEntity(BenefitTypeRequest request) {
        BenefitTypeEntity entity = new BenefitTypeEntity();
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        return entity;
    }
}
