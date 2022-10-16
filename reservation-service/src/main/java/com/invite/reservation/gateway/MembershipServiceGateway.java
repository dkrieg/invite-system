package com.invite.reservation.gateway;

import com.invite.membership.domain.Membership;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MEMBERSHIP-SERVICE")
public interface MembershipServiceGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    Membership getMembership(@RequestParam("id") Long id);
}
