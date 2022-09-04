package com.invite.organization.mapper;

import com.invite.organization.domain.Community;
import com.invite.organization.domain.CommunityRequest;
import com.invite.organization.entity.CommunityEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class CommunityMapper {
    public Community toDomain(CommunityEntity entity) {
        return Community.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public CommunityEntity toEntity(CommunityRequest request) {
        return toEntity(new CommunityEntity(), request);
    }

    public CommunityEntity toEntity(CommunityEntity entity, CommunityRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
