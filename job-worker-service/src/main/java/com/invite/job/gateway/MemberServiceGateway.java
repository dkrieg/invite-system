package com.invite.job.gateway;

import com.invite.member.domain.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MEMBER-SERVICE")
public interface MemberServiceGateway {

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    Member getMember(@PathVariable("id") Long id);
}
