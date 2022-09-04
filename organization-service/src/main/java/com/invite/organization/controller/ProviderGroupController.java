package com.invite.organization.controller;

import com.invite.organization.domain.ProviderGroup;
import com.invite.organization.domain.ProviderGroupRequest;
import com.invite.organization.service.DomainService;
import com.invite.organization.service.ProviderGroupDomainService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping(path = "/provider-groups")
public class ProviderGroupController {
    ProviderGroupDomainService service;

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-provider-groups",summary = "Get All Provider Groups")
    Collection<ProviderGroup> getProviderGroups() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-provider-group", summary = "Create New Provider Group")
    ResponseEntity<ProviderGroup> createProviderGroups(@RequestBody ProviderGroupRequest request) {
        ProviderGroup saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/provider-groups/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-provider-group", summary = "Get Provider Group By ID")
    ResponseEntity<ProviderGroup> getProviderGroup(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-provider-group", summary = "Update Provider Group By ID")
    ResponseEntity<ProviderGroup> updateProviderGroups(@PathVariable("id") Long id, @RequestBody ProviderGroupRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-provider-group", summary = "Delete Provider Group By ID")
    ResponseEntity<Void> deleteProviderGroups(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
