package com.invite.job.gateway;

import com.invite.organization.domain.Community;
import com.invite.organization.domain.Organization;
import com.invite.organization.domain.OrganizationRequest;
import com.invite.organization.domain.OrganizationSegment;
import com.invite.organization.domain.ProviderGroup;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("ORGANIZATION-SERVICE")
public interface OrganizationServiceGateway {
    @GetMapping(path = "/provider-groups", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<ProviderGroup> getProviderGroups();

    @GetMapping(path = "/communities", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<Community> getCommunities();

    @GetMapping(path = "/organization-segments", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    List<OrganizationSegment> getOrganizationSegments();

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Organization createOrganization(OrganizationRequest request);

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    Organization getOrganization(@PathVariable("id") Long organizationId);
}
