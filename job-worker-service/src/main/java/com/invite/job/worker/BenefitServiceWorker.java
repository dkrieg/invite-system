package com.invite.job.worker;

import com.invite.job.domain.BenefitPackageRequestVariable;
import com.invite.job.domain.BenefitPackageVariable;
import com.invite.job.domain.BenefitsVariable;
import com.invite.job.gateway.BenefitServiceGateway;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitServiceWorker {
    BenefitServiceGateway gateway;

    @ZeebeWorker(type = "get-benefits", autoComplete = true)
    public BenefitsVariable handleGetBenefits() {
        return new BenefitsVariable(gateway.getBenefits());
    }

    @ZeebeWorker(type = "create-benefit-package", autoComplete = true)
    public BenefitPackageVariable handleCreateBenefitPackage(@ZeebeVariablesAsType BenefitPackageRequestVariable variable) {
        return new BenefitPackageVariable(gateway.createBenefitPackage(variable.getBenefitPackage()));
    }
}
