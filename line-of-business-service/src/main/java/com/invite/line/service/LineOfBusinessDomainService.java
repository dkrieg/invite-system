package com.invite.line.service;

import com.invite.domain.service.DomainService;
import com.invite.line.domain.LineOfBusiness;
import com.invite.line.domain.LineOfBusinessRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LineOfBusinessDomainService extends DomainService<LineOfBusiness, LineOfBusinessRequest, Long> {
}
