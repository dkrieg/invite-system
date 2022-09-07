package com.invite.benefit.service;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;

import java.util.Collection;

public interface BenefitDomainService extends DomainService<Benefit, BenefitRequest, Long>  {
    Collection<Benefit> fetchAllByOrganizationId(Long Benefit);

}
