package com.invite.provider.group.controller;

import com.invite.provider.group.domain.ProviderGroup;
import com.invite.provider.group.domain.ProviderGroupRequest;
import com.invite.provider.group.service.ProviderGroupDomainService;
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
public class ProviderGroupController {
    ProviderGroupDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-provider-groups", summary = "Get All Provider Groups")
    Collection<ProviderGroup> getProviderGroups() {
        return service.fetchAll();
    }

    @GetMapping(path = "/ids", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-provider-groups-in-list", summary = "Get All Provider Groups By ID List")
    Collection<ProviderGroup> getProviderGroupsByIds(@RequestParam("id") List<Long> ids) {
        return service.fetchAllById(ids);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-provider-group", summary = "Create New Provider Group")
    ResponseEntity<ProviderGroup> createProviderGroup(@RequestBody ProviderGroupRequest request) {
        ProviderGroup saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
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
    ResponseEntity<ProviderGroup> updateProviderGroup(@PathVariable("id") Long id, @RequestBody ProviderGroupRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-provider-group", summary = "Delete Provider Group By ID")
    ResponseEntity<Void> deleteProviderGroup(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
