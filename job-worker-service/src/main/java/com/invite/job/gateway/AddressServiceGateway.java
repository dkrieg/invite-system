package com.invite.job.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("ADDRESS-SERVICE")
public interface AddressServiceGateway {
    @GetMapping(path = "/states", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getStates();
}
