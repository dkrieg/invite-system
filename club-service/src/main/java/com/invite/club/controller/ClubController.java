package com.invite.club.controller;

import com.invite.club.domain.Club;
import com.invite.club.domain.ClubRequest;
import com.invite.club.service.ClubDomainService;
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
public class ClubController {
    ClubDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-clubs", summary = "Get All Clubs")
    Collection<Club> getClubs() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-club", summary = "Create New Club")
    ResponseEntity<Club> createClub(@RequestBody ClubRequest request) {
        Club saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-club", summary = "Get Club By ID")
    ResponseEntity<Club> getClub(@PathVariable("id") Long clubId) {
        return ResponseEntity.of(service.fetchById(clubId));
    }

    @GetMapping(path = "/by-name/{name}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-club", summary = "Get Club By Name")
    ResponseEntity<Club> getClubByName(@PathVariable("name") String name) {
        return ResponseEntity.of(service.fetchByName(name));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-club", summary = "Update Club By ID")
    ResponseEntity<Club> updateClub(@PathVariable("id") Long clubId, @RequestBody ClubRequest request) {
        return ResponseEntity.of(service.updateById(clubId, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-club", summary = "Delete Club By ID")
    ResponseEntity<Void> deleteClub(@PathVariable("id") Long clubId) {
        return service.deleteById(clubId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
