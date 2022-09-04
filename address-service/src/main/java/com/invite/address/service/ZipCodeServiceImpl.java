package com.invite.address.service;

import com.invite.address.domain.ZipCode;
import com.invite.address.mapper.ZipCodeMapper;
import com.invite.address.repository.ZipCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
class ZipCodeServiceImpl implements ZipCodeService {
    ZipCodeRepository repository;
    ZipCodeMapper mapper;

    @Override
    public Set<ZipCode> fetchByState(String state) {
        return repository.findByStatesAbbreviation(state)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toSet());
    }
}
