package com.invite.organization.mapper;

import com.invite.organization.domain.ProviderGroup;
import com.invite.organization.domain.ProviderGroupRequest;
import com.invite.organization.entity.ProviderGroupEntity;
import com.invite.organization.repository.ProviderGroupTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProviderGroupMapper {
    ProviderGroupTypeRepository repository;
    ProviderGroupTypeMapper mapper;
    public ProviderGroup toDomain(ProviderGroupEntity entity) {
        return ProviderGroup.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(mapper.toDomain(entity.getProviderGroupType()))
                .build();
    }

    public ProviderGroupEntity toEntity(ProviderGroupRequest request) {
        return toEntity(new ProviderGroupEntity(), request);
    }

    public ProviderGroupEntity toEntity(ProviderGroupEntity entity, ProviderGroupRequest request) {
        entity.setName(request.getName());
        entity.setProviderGroupType(repository.getReferenceById(request.getProviderGroupTypeCode()));
        return entity;
    }
}
