package com.invite.job.gateway;

import com.invite.amenity.domain.Amenity;
import com.invite.amenity.domain.AmenityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("AMENITY-SERVICE")
public interface AmenityServiceGateway {
    @GetMapping(path = "/amenity-types", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<String> getAmenityTypes();

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Amenity createAmenity(AmenityRequest request);

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    Amenity getAmenity(@PathVariable("id") Long id);
}
