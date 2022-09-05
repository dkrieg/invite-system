package com.invite.job.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("AMENITY-SERVICE")
public interface AmenityServiceGateway {
    @GetMapping(path = "/types/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<String> getAmenityTypes();

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Map<String, Object> createAmenity(Map<String, Object> request);
}
