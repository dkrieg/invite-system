package com.invite.benefit.service;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.benefit.domain.BenefitPackageRequest;

import java.util.Collection;

public interface BenefitPackageDomainService extends DomainService<BenefitPackage, BenefitPackageRequest, Long> {
    Collection<BenefitPackage> fetchAllByOrganizationId(Long Benefit);

}
