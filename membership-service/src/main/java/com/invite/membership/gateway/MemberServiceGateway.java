package com.invite.membership.gateway;

import com.invite.member.domain.Member;
import com.invite.member.domain.MemberRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MEMBER-SERVICE")
public interface MemberServiceGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Member getMember(@PathVariable("id") Long id);

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Member createMember(MemberRequest request);

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Member updateMember(@PathVariable("id") Long addressId, MemberRequest request);

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    void deleteMember(@PathVariable("id") Long memberId);
}
