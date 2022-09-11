package com.invite.membership.controller;

import com.invite.membership.domain.MembershipLevelRequest;
import com.invite.membership.service.MembershipLevelDomainService;
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
@RequestMapping(path = "/membership-levels")
public class MembershipLevelController {
    MembershipLevelDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-membership-levels", summary = "Get All Membership Levels")
    Collection<String> getMembershipLevels() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-membership-level", summary = "Create New Membership Level")
    ResponseEntity<String> createMembershipLevel(@RequestBody MembershipLevelRequest request) {
        String saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/membership-levels/{id}")
                        .build("id", saved))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-membership-level", summary = "Get Membership Level By ID")
    ResponseEntity<String> getMembershipLevel(@PathVariable("id") String id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-membership-level", summary = "Delete Membership Level By ID")
    ResponseEntity<Void> deleteMembershipLevel(@PathVariable("id") String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
