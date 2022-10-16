package com.invite.member.service;

import com.invite.domain.service.DomainService;
import com.invite.member.domain.Member;
import com.invite.member.domain.MemberRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface MemberDomainService extends DomainService<Member, MemberRequest, Long> {
    Optional<Member> fetchByLoginId(String loginId);
}
