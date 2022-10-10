package com.invite.membership.service;

import com.invite.domain.service.DomainService;
import com.invite.membership.domain.MembershipLevelRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface MembershipLevelDomainService extends DomainService<String, MembershipLevelRequest, String> {
    @Override
    default Optional<String> updateById(String id, MembershipLevelRequest request) {
        throw new UnsupportedOperationException();
    }
}
