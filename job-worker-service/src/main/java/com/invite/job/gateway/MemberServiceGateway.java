package com.invite.job.gateway;

import com.invite.member.domain.Member;
import com.invite.member.domain.MemberRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MEMBER-SERVICE")
public interface MemberServiceGateway {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Member createMember(MemberRequest request);
}
