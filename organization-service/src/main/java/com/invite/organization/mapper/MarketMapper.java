package com.invite.organization.mapper;

import com.invite.organization.domain.Market;
import com.invite.organization.domain.MarketRequest;
import com.invite.organization.entity.MarketEntity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class MarketMapper {
    public Market toDomain(MarketEntity entity) {
        return Market.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public MarketEntity toEntity(MarketRequest request) {
        return toEntity(new MarketEntity(), request);
    }

    public MarketEntity toEntity(MarketEntity entity, MarketRequest request) {
        entity.setName(request.getName());
        return entity;
    }
}
