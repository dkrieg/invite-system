package com.invite.address;

import com.invite.address.entity.AddressEntity;
import com.invite.address.entity.StateEntity;
import com.invite.address.entity.ZipCodeEntity;
import com.invite.address.repository.AddressRepository;
import com.invite.address.repository.StateRepository;
import com.invite.address.repository.ZipCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassLoaderUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.*;
import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
@Slf4j
class DatabaseStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    ZipCodeRepository zipCodeRepository;
    StateRepository stateRepository;
    AddressRepository addressRepository;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addressRepository.deleteAll();
        zipCodeRepository.deleteAll();
        InputStream resource = new ClassPathResource("zipcodes.txt").getInputStream();
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(resource)).lines()) {
            lines.forEach(line -> {
                String[] fields = line.split(" ");
                String zipcode = fields[0];
                Set<StateEntity> states = Stream.of(fields[1].split(","))
                        .map(stateRepository::getReferenceById)
                        .collect(Collectors.toSet());
                zipCodeRepository.saveAndFlush(ZipCodeEntity.builder()
                        .postalCode(zipcode)
                        .states(states)
                        .build());
            });
        }
    }
}