package com.invite.benefit.service;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BenefitDomainService extends DomainService<Benefit, BenefitRequest, Long> {
}
