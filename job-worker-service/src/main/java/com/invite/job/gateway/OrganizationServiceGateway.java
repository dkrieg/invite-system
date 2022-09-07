package com.invite.job.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("ORGANIZATION-SERVICE")
public interface OrganizationServiceGateway {
    @GetMapping(path = "/provider-groups", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getProviderGroups();

    @GetMapping(path = "/communities", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getCommunities();

    @GetMapping(path = "/markets", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getMarkets();

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Map<String, Object> createOrganization(Map<String, Object> request);
}
