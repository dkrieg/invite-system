package com.invite.address.mapper;

import com.invite.address.domain.State;
import com.invite.address.entity.StateEntity;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {
    public State toDomain(StateEntity stateEntity) {
        return State.builder()
                .abbreviation(stateEntity.getAbbreviation())
                .name(stateEntity.getName())
                .build();
    }
}
