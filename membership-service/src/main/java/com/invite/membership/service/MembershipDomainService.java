package com.invite.membership.service;

import com.invite.domain.service.DomainService;
import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MembershipDomainService extends DomainService<Membership, MembershipRequest, Long> {
}
