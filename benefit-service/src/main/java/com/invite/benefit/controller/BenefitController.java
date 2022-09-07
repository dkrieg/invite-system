package com.invite.benefit.controller;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.benefit.service.BenefitDomainService;
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
public class BenefitController {
    BenefitDomainService service;

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefits",summary = "Get All Benefits")
    Collection<Benefit> getBenefits() {
        return service.fetchAll();
    }

    @GetMapping(path = "/with-organization/{organizationId}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefits-with-organization",summary = "Get All Benefits")
    Collection<Benefit> getBenefitsWithOrganization(@PathVariable("organizationId") Long organizationId) {
        return service.fetchAllByOrganizationId(organizationId);
    }

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-benefit", summary = "Create New Benefit")
    ResponseEntity<Benefit> createBenefit(@RequestBody BenefitRequest request) {
        Benefit saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefit", summary = "Get Benefit By ID")
    ResponseEntity<Benefit> getBenefit(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-benefit", summary = "Update Benefit By ID")
    ResponseEntity<Benefit> updateBenefit(@PathVariable("id") Long id, @RequestBody BenefitRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-benefit", summary = "Delete Benefit By ID")
    ResponseEntity<Void> deleteBenefit(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
