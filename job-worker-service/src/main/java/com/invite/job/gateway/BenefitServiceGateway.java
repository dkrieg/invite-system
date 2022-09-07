package com.invite.job.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("BENEFIT-SERVICE")
public interface BenefitServiceGateway {
    @GetMapping(path = "/benefit-types", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getBenefitTypes();

    @GetMapping(path = "/with-organization/{organizationId}", consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getBenefitsWithOrganization(@PathVariable("organizationId") Long organizationId);

    @GetMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getBenefits();

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Map<String, Object> createBenefit(Map<String, Object> request);

    @PostMapping(path = "/benefit-packages", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Map<String, Object> createBenefitPackage(Map<String, Object> request);
}
