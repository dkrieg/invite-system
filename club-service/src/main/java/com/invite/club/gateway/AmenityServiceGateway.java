package com.invite.club.gateway;

import com.invite.amenity.domain.Amenity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("AMENITY-SERVICE")
public interface AmenityServiceGateway {
    @GetMapping(path = "/ids", produces = APPLICATION_JSON_VALUE)
    List<Amenity> getAmenitiesByIds(@RequestParam("id") List<Long> ids);
}
