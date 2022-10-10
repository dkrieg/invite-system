package com.invite.line.controller;

import com.invite.line.domain.LineOfBusiness;
import com.invite.line.domain.LineOfBusinessRequest;
import com.invite.line.service.LineOfBusinessDomainService;
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
public class LineOfBusinessController {
    LineOfBusinessDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-lines-of-business", summary = "Get All Lines Of Business")
    Collection<LineOfBusiness> getLinesOfBusiness() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-line-of-business", summary = "Create New Line Of Business")
    ResponseEntity<LineOfBusiness> createLineOfBusiness(@RequestBody LineOfBusinessRequest request) {
        LineOfBusiness saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-line-of-business", summary = "Get Line Of Business By ID")
    ResponseEntity<LineOfBusiness> getLineOfBusiness(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-line-of-business", summary = "Update Line Of Business By ID")
    ResponseEntity<LineOfBusiness> updateLineOfBusiness(@PathVariable("id") Long id, @RequestBody LineOfBusinessRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-line-of-business", summary = "Delete Line Of Business By ID")
    ResponseEntity<Void> deleteLineOfBusiness(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
