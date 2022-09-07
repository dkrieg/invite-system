package com.invite.benefit.controller;

import com.invite.benefit.domain.BenefitType;
import com.invite.benefit.domain.BenefitTypeRequest;
import com.invite.benefit.service.BenefitTypeDomainService;
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
@RequestMapping(path = "/benefit-types")
public class BenefitTypeController {
    BenefitTypeDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-benefit-types",summary = "Get All Benefit Types")
    Collection<BenefitType> getBenefits() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-benefit-type", summary = "Create New Benefit Type")
    ResponseEntity<BenefitType> createBenefitType(@RequestBody BenefitTypeRequest request) {
        BenefitType saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/benefit-types/{id}")
                        .build("id", saved.getCode()))
                .body(saved);
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-benefit-type", summary = "Delete Benefit Type By ID")
    ResponseEntity<Void> deleteBenefitType(@PathVariable("id") String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
