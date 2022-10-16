package com.invite.membership.gateway;

import com.invite.benefit.domain.BenefitPackage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("BENEFIT-SERVICE")
public interface BenefitServiceGateway {
    @GetMapping(path = "/benefit-packages/{id}", produces = APPLICATION_JSON_VALUE)
    BenefitPackage getBenefitPackage(@RequestParam("id") Long id);
}
