package com.invite.job.gateway;

import com.invite.membership.domain.Membership;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MEMBERSHIP-SERVICE")
public interface MembershipServiceGateway {

    @GetMapping(path = "/by-member-id/{id}", produces = APPLICATION_JSON_VALUE)
    Membership getMembershipByMemberId(@PathVariable("id") Long id);
}
