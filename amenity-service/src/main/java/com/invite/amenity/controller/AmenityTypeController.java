package com.invite.amenity.controller;

import com.invite.amenity.domain.AmenityTypeRequest;
import com.invite.amenity.service.AmenityTypeDomainService;
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
@RequestMapping(path = "/types")
public class AmenityTypeController {
    AmenityTypeDomainService service;

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-amenity-types",summary = "Get All Amenity Types")
    Collection<String> getAmenityTypes() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-amenity-type", summary = "Create New Amenity Type")
    ResponseEntity<String> createAmenityType(@RequestBody AmenityTypeRequest request) {
        String saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{name}")
                        .build("name", saved))
                .body(saved);
    }

    @DeleteMapping(path = "/{name}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-amenity-type", summary = "Delete Amenity Type By ID")
    ResponseEntity<Void> deleteAmenityType(@PathVariable("name") String name) {
        return service.deleteById(name)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
