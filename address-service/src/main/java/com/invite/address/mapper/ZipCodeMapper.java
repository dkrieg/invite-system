package com.invite.address.mapper;

import com.invite.address.domain.ZipCode;
import com.invite.address.entity.ZipCodeEntity;
import org.springframework.stereotype.Component;

@Component
public class ZipCodeMapper {
    public ZipCode toDomain(ZipCodeEntity zipCodeEntity) {
        return ZipCode.builder()
                .postalCode(zipCodeEntity.getPostalCode())
                .plusFour(zipCodeEntity.getPlusFour())
                .build();
    }
}
