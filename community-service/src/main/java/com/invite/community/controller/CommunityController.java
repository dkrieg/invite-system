package com.invite.community.controller;

import com.invite.community.domain.Community;
import com.invite.community.domain.CommunityRequest;
import com.invite.community.service.CommunityDomainService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
public class CommunityController {
    CommunityDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-communities", summary = "Get All Communities")
    Collection<Community> getCommunities() {
        return service.fetchAll();
    }

    @GetMapping(path = "/ids", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-communities-in-list", summary = "Get All Communities By ID List")
    Collection<Community> getCommunitiesByIds(@RequestParam("id") List<Long> ids) {
        return service.fetchAllById(ids);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-community", summary = "Create New Community")
    ResponseEntity<Community> createCommunity(@RequestBody CommunityRequest request) {
        Community saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-community", summary = "Get Community By ID")
    ResponseEntity<Community> getCommunity(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-community", summary = "Update Community By ID")
    ResponseEntity<Community> updateCommunity(@PathVariable("id") Long id, @RequestBody CommunityRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-community", summary = "Delete Community By ID")
    ResponseEntity<Void> deleteCommunity(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
