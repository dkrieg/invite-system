package com.invite.membership.controller;

import com.invite.membership.domain.Membership;
import com.invite.membership.domain.MembershipRequest;
import com.invite.membership.service.MembershipDomainService;
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
public class MembershipController {
    MembershipDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-memberships", summary = "Get All Memberships")
    Collection<Membership> getMemberships() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-membership", summary = "Create New Membership")
    ResponseEntity<Membership> createMembership(@RequestBody MembershipRequest request) {
        Membership saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-membership", summary = "Get Membership By ID")
    ResponseEntity<Membership> getMembership(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @GetMapping(path = "/by-member-id/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-membership-by-member-id", summary = "Get Membership By Member ID")
    ResponseEntity<Membership> getMembershipByMemberId(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchByMembersId(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-membership", summary = "Update Membership By ID")
    ResponseEntity<Membership> updateMembership(@PathVariable("id") Long id, @RequestBody MembershipRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-membership", summary = "Delete Membership By ID")
    ResponseEntity<Void> deleteMembership(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
