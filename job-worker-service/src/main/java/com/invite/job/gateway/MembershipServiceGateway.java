package com.invite.job.gateway;

import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MEMBERSHIP-SERVICE")
public interface MembershipServiceGateway {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Membership createMembership(MembershipRequest request);
}
