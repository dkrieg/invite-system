package com.invite.job.gateway;

import com.invite.amenity.domain.Amenity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("AMENITY-SERVICE")
public interface AmenityServiceGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    Amenity getAmenity(@PathVariable("id") Long id);
}
