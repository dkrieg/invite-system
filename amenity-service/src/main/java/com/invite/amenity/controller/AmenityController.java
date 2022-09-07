package com.invite.amenity.controller;

import com.invite.amenity.domain.Amenity;
import com.invite.amenity.domain.AmenityRequest;
import com.invite.amenity.service.AmenityDomainService;
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
public class AmenityController {
    AmenityDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-amenities",summary = "Get All Amenities")
    Collection<Amenity> getAmenities() {
        return service.fetchAll();
    }

    @GetMapping(path = "/with-organization/{organizationId}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-amenities-with-organization",summary = "Get All Amenities With Organization")
    Collection<Amenity> getAmenitiesWithOrganization(@PathVariable("organizationId") Long organizationId) {
        return service.fetchAllByOrganization(organizationId);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-amenity", summary = "Create New Amenity")
    ResponseEntity<Amenity> createAmenity(@RequestBody AmenityRequest request) {
        Amenity saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-amenity", summary = "Get Amenity By ID")
    ResponseEntity<Amenity> getAmenity(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-amenity", summary = "Update Amenity By ID")
    ResponseEntity<Amenity> updateAmenity(@PathVariable("id") Long id, @RequestBody AmenityRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-amenity", summary = "Delete Amenity By ID")
    ResponseEntity<Void> deleteAmenity(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
