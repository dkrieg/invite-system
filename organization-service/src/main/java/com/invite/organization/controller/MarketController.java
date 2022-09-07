package com.invite.organization.controller;

import com.invite.organization.domain.Market;
import com.invite.organization.domain.MarketRequest;
import com.invite.organization.service.DomainService;
import com.invite.organization.service.MarketDomainService;
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
@RequestMapping(path = "/markets")
public class MarketController {
    MarketDomainService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-markets",summary = "Get All Markets")
    Collection<Market> getMarkets() {
        return service.fetchAll();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-market", summary = "Create New Market")
    ResponseEntity<Market> createMarket(@RequestBody MarketRequest request) {
        Market saved = service.create(request);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/markets/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-market", summary = "Get Market By ID")
    ResponseEntity<Market> getMarket(@PathVariable("id") Long id) {
        return ResponseEntity.of(service.fetchById(id));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-market", summary = "Update Market By ID")
    ResponseEntity<Market> updateMarket(@PathVariable("id") Long id, @RequestBody MarketRequest request) {
        return ResponseEntity.of(service.updateById(id, request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-market", summary = "Delete Market By ID")
    ResponseEntity<Void> deleteMarket(@PathVariable("id") Long id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
