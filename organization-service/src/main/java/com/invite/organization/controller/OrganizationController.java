package com.invite.organization.controller;

import com.invite.organization.domain.Organization;
import com.invite.organization.domain.OrganizationRequest;
import com.invite.organization.service.OrganizationDomainService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
public class OrganizationController {
    OrganizationDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-organizations", summary = "Get All Organizations")
    Collection<Organization> getOrganizations() {
        return service.fetchAll();
    }

    @GetMapping(path = "/with-provider-group/{providerGroupId}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-organizations-with-provider-group", summary = "Get All Organizations With Provider Group")
    Collection<Organization> getOrganizationsWithProviderGroup(@PathVariable("providerGroupId") Long providerGroupId) {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-organization", summary = "Create New Organization")
    ResponseEntity<Organization> createOrganization(@RequestBody OrganizationRequest request) {
        Organization saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-organization", summary = "Get Organization By ID")
    ResponseEntity<Organization> getOrganization(@PathVariable("id") Long addressId) {
        return ResponseEntity.of(service.fetchById(addressId));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-organization", summary = "Update Organization By ID")
    ResponseEntity<Organization> updateOrganization(@PathVariable("id") Long addressId, @RequestBody OrganizationRequest request) {
        return ResponseEntity.of(service.updateById(addressId, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-organization", summary = "Delete Organization By ID")
    ResponseEntity<Void> deleteOrganization(@PathVariable("id") Long addressId) {
        return service.deleteById(addressId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
