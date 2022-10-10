package com.invite.club.controller;

import com.invite.club.domain.ClubSegment;
import com.invite.club.domain.ClubSegmentRequest;
import com.invite.club.service.ClubSegmentDomainService;
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
@RequestMapping(path = "/club-segments")
public class ClubSegmentController {
    ClubSegmentDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-club-segments", summary = "Get All Club Segments")
    Collection<ClubSegment> getClubSegments() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-club-segment", summary = "Create New Club Segment")
    ResponseEntity<ClubSegment> createClubSegment(@RequestBody ClubSegmentRequest request) {
        ClubSegment saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/club-segments/{id}")
                        .build("id", saved.getColor()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-club-segment", summary = "Get Club Segment By ID")
    ResponseEntity<ClubSegment> getClubSegment(@PathVariable("id") String id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-club-segment", summary = "Delete Club Segment By ID")
    ResponseEntity<Void> deleteClubSegment(@PathVariable("id") String id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
