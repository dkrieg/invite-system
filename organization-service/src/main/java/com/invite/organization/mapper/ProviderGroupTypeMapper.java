package com.invite.organization.mapper;

import com.invite.organization.domain.ProviderGroupType;
import com.invite.organization.domain.ProviderGroupTypeRequest;
import com.invite.organization.entity.ProviderGroupTypeEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProviderGroupTypeMapper {
    public ProviderGroupType toDomain(ProviderGroupTypeEntity entity) {
        return ProviderGroupType.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .build();
    }

    public ProviderGroupTypeEntity toEntity(ProviderGroupTypeRequest request) {
        return toEntity(new ProviderGroupTypeEntity(), request);
    }

    public ProviderGroupTypeEntity toEntity(ProviderGroupTypeEntity entity, ProviderGroupTypeRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
