package com.invite.club.mapper;

import com.invite.club.domain.ClubSegment;
import com.invite.club.domain.ClubSegmentRequest;
import com.invite.club.entity.ClubSegmentEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ClubSegmentMapper {
    public ClubSegment toDomain(ClubSegmentEntity entity) {
        return ClubSegment.builder()
                .color(entity.getColor())
                .name(entity.getName())
                .build();
    }

    public ClubSegmentEntity toEntity(ClubSegmentRequest request) {
        return toEntity(new ClubSegmentEntity(), request);
    }

    public ClubSegmentEntity toEntity(ClubSegmentEntity entity, ClubSegmentRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
