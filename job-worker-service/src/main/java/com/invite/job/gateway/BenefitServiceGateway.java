package com.invite.job.gateway;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitPackage;
import com.invite.benefit.domain.BenefitPackageRequest;
import com.invite.benefit.domain.BenefitRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("BENEFIT-SERVICE")
public interface BenefitServiceGateway {

    @GetMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Benefit> getBenefits();

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Benefit createBenefit(BenefitRequest request);

    @PostMapping(path = "/benefit-packages", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    BenefitPackage createBenefitPackage(BenefitPackageRequest request);
}
