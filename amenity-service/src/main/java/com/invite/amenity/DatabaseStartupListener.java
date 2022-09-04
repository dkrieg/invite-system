package com.invite.amenity;

import com.invite.amenity.entity.AmenityTypeEntity;
import com.invite.amenity.repository.AmenityRepository;
import com.invite.amenity.repository.AmenityTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
@Slf4j
class DatabaseStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    AmenityTypeRepository amenityTypeRepository;
    AmenityRepository amenityRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        amenityRepository.deleteAll();
        amenityTypeRepository.deleteAll();
        amenityTypeRepository.saveAllAndFlush(List.of(
                new AmenityTypeEntity("RESTAURANT"),
                new AmenityTypeEntity("SPA"),
                new AmenityTypeEntity("GOLF"),
                new AmenityTypeEntity("PROGRAM"),
                new AmenityTypeEntity("POOL"),
                new AmenityTypeEntity("SERVICE"),
                new AmenityTypeEntity("GROUP"),
                new AmenityTypeEntity("AREA")
        ));
    }
}