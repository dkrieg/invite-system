package com.invite.line.mapper;

import com.invite.line.domain.LineOfBusiness;
import com.invite.line.domain.LineOfBusinessRequest;
import com.invite.line.entity.LineOfBusinessEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class LineOfBusinessMapper {

    public LineOfBusiness toDomain(LineOfBusinessEntity entity) {
        return LineOfBusiness.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public LineOfBusinessEntity toEntity(LineOfBusinessRequest request) {
        return toEntity(new LineOfBusinessEntity(), request);
    }

    public LineOfBusinessEntity toEntity(LineOfBusinessEntity entity, LineOfBusinessRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
