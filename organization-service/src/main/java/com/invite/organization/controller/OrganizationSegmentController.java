package com.invite.organization.controller;

import com.invite.organization.domain.OrganizationSegment;
import com.invite.organization.domain.OrganizationSegmentRequest;
import com.invite.organization.service.OrganizationSegmentDomainService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/organization-segments")
public class OrganizationSegmentController {
    OrganizationSegmentDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-organization-segments", summary = "Get All Organization Segments")
    Collection<OrganizationSegment> getOrganizationSegments() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-organization-segment", summary = "Create New Organization Segment")
    ResponseEntity<OrganizationSegment> createOrganizationSegment(@RequestBody OrganizationSegmentRequest request) {
        OrganizationSegment saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/organization-segments/{id}")
                        .build("id", saved.getColor()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-organization-segment", summary = "Get Organization Segment By ID")
    ResponseEntity<OrganizationSegment> getOrganizationSegment(@PathVariable("id") String id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-organization-segment", summary = "Delete Organization Segment By ID")
    ResponseEntity<Void> deleteOrganizationSegment(@PathVariable("id") String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
