package com.invite.membership.service;

import com.invite.domain.service.DomainService;
import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface MembershipDomainService extends DomainService<Membership, MembershipRequest, Long> {
    Optional<Membership> fetchByMembersId(Long memberId);
}
