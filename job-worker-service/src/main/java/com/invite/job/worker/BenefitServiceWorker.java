package com.invite.job.worker;

import com.invite.job.gateway.BenefitServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitServiceWorker {
    BenefitServiceGateway gateway;

    @ZeebeWorker(type = "get-benefit-types", autoComplete = true)
    public Map<String, Object> handleGetBenefitTypes() {
        return Map.of("benefitTypes", gateway.getBenefitTypes());
    }

    @ZeebeWorker(type = "get-benefits-with-organization", autoComplete = true)
    public Map<String, Object> handleGetBenefitsWithOrganization(@ZeebeVariable Integer organizationId) {
        return Map.of("benefits", gateway.getBenefitsWithOrganization(organizationId.longValue()));
    }

    @ZeebeWorker(type = "create-benefit", autoComplete = true)
    public Map<String, Object> handleCreateBenefit(@ZeebeVariable Map<String, Object> benefit) {
        return Map.of("benefit", gateway.createBenefit(benefit));
    }

    @ZeebeWorker(type = "create-benefit-package", autoComplete = true)
    public Map<String, Object> handleCreateBenefitPackage(@ZeebeVariable Map<String, Object> benefitPackage) {
        return Map.of("benefitPackage", gateway.createBenefitPackage(benefitPackage));
    }
}
