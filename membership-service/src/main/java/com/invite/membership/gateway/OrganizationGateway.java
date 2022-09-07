package com.invite.membership.gateway;

import com.invite.organization.domain.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("ORGANIZATION-SERVICE")
public interface OrganizationGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Organization getOrganization(@PathVariable("id") Long id);
}
