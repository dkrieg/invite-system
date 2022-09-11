package com.invite.organization.controller;

import com.invite.organization.domain.ProviderGroupType;
import com.invite.organization.domain.ProviderGroupTypeRequest;
import com.invite.organization.service.ProviderGroupTypeDomainService;
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
@RequestMapping(path = "/provider-group-types")
public class ProviderGroupTypeController {
    ProviderGroupTypeDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-provider-group-types", summary = "Get All Provider Group Types")
    Collection<ProviderGroupType> getProviderGroups() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-provider-group-type", summary = "Create New Provider Group Type")
    ResponseEntity<ProviderGroupType> createProviderGroupType(@RequestBody ProviderGroupTypeRequest request) {
        ProviderGroupType saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/provider-group-types/{id}")
                        .build("id", saved.getCode()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-provider-group-type", summary = "Get Provider Group Type By ID")
    ResponseEntity<ProviderGroupType> getProviderGroupType(@PathVariable("id") String id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-provider-group", summary = "Delete Provider Group Type By ID")
    ResponseEntity<Void> deleteProviderGroupType(@PathVariable("id") String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
