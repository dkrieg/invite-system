package com.invite.address.service;

import com.invite.address.domain.State;
import com.invite.address.mapper.StateMapper;
import com.invite.address.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class StateServiceImpl implements StateService {
    StateRepository repository;
    StateMapper mapper;

    @Override
    public List<State> fetchAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .sorted(Comparator.comparing(State::getAbbreviation))
                .collect(Collectors.toList());
    }
}
