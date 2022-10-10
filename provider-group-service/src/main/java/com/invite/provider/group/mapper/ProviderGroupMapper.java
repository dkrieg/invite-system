package com.invite.provider.group.mapper;

import com.invite.provider.group.domain.ProviderGroup;
import com.invite.provider.group.domain.ProviderGroupRequest;
import com.invite.provider.group.entity.ProviderGroupEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProviderGroupMapper {

    public ProviderGroup toDomain(ProviderGroupEntity entity) {
        return ProviderGroup.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public ProviderGroupEntity toEntity(ProviderGroupRequest request) {
        return toEntity(new ProviderGroupEntity(), request);
    }

    public ProviderGroupEntity toEntity(ProviderGroupEntity entity, ProviderGroupRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
