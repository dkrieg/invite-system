package com.invite.benefit.controller;

import com.invite.benefit.domain.BenefitTierRequest;
import com.invite.benefit.service.BenefitTierDomainService;
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
@RequestMapping(path = "/benefit-tiers")
public class BenefitTierController {
    BenefitTierDomainService service;

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefit-tiers",summary = "Get All Benefit Tiers")
    Collection<String> getBenefits() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-benefit-tier", summary = "Create New Benefit Tier")
    ResponseEntity<String> createBenefitTier(@RequestBody BenefitTierRequest request) {
        String saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/benefit-tiers/{id}")
                        .build("id", saved))
                .body(saved);
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-benefit-tier", summary = "Delete Benefit Tier By ID")
    ResponseEntity<Void> deleteBenefitTier(@PathVariable("id") String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
