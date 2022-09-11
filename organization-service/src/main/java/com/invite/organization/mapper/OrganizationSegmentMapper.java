package com.invite.organization.mapper;

import com.invite.organization.domain.OrganizationSegment;
import com.invite.organization.domain.OrganizationSegmentRequest;
import com.invite.organization.entity.OrganizationSegmentEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class OrganizationSegmentMapper {
    public OrganizationSegment toDomain(OrganizationSegmentEntity entity) {
        return OrganizationSegment.builder()
                .color(entity.getColor())
                .name(entity.getName())
                .build();
    }

    public OrganizationSegmentEntity toEntity(OrganizationSegmentRequest request) {
        return toEntity(new OrganizationSegmentEntity(), request);
    }

    public OrganizationSegmentEntity toEntity(OrganizationSegmentEntity entity, OrganizationSegmentRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
