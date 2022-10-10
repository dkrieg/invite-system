package com.invite.benefit.service;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.benefit.domain.BenefitPackageRequest;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public interface BenefitPackageDomainService extends DomainService<BenefitPackage, BenefitPackageRequest, Long> {
    Collection<BenefitPackage> fetchAllById(Iterable<Long> ids);

    Optional<BenefitPackage> fetchByName(String name);
}
