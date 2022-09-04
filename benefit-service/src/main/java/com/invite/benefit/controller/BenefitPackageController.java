package com.invite.benefit.controller;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.benefit.domain.BenefitPackageRequest;
import com.invite.benefit.service.BenefitPackageDomainService;
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
@RequestMapping(path = "/benefit-packages")
public class BenefitPackageController {
    BenefitPackageDomainService service;

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefit-packages",summary = "Get All Benefit Packages")
    Collection<BenefitPackage> getBenefitPackages() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-benefit-package", summary = "Create New Benefit Package")
    ResponseEntity<BenefitPackage> createBenefitPackage(@RequestBody BenefitPackageRequest request) {
        BenefitPackage saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/benefit-packages/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefit-package", summary = "Get Benefit Package By ID")
    ResponseEntity<BenefitPackage> getBenefitPackage(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-benefit-package", summary = "Update Benefit Package By ID")
    ResponseEntity<BenefitPackage> updateBenefitPackage(@PathVariable("id") Long id, @RequestBody BenefitPackageRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-benefit-package", summary = "Delete Benefit Package By ID")
    ResponseEntity<Void> deleteBenefitPackage(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
