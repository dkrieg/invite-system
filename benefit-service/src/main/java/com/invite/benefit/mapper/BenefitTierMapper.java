package com.invite.benefit.mapper;

import com.invite.benefit.domain.BenefitTierRequest;
import com.invite.benefit.entity.BenefitTierEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitTierMapper {

    public String toDomain(BenefitTierEntity entity) {
        return entity.getName();
    }

    public BenefitTierEntity toEntity(BenefitTierRequest request) {
        BenefitTierEntity entity = new BenefitTierEntity();
        entity.setName(request.getName());
        return entity;
    }
}
