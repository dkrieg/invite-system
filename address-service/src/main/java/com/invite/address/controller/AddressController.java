package com.invite.address.controller;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import com.invite.address.domain.State;
import com.invite.address.domain.ZipCode;
import com.invite.address.service.AddressService;
import com.invite.address.service.StateService;
import com.invite.address.service.ZipCodeService;
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
public class AddressController {
    StateService stateService;
    ZipCodeService zipCodeService;
    AddressService service;

    @GetMapping(path = "/states", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-states",summary = "Get All States")
    Collection<State> getStates() {
        return stateService.fetchAll();
    }

    @GetMapping(path = "/{state}/zip-codes", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-zip-codes-for-state",summary = "Get Zip Codes For State")
    Collection<ZipCode> getZipCodesWithPostalCode(@PathVariable("state") String state) {
        return zipCodeService.fetchByState(state);
    }

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "create-address", summary = "Create New Address")
    ResponseEntity<Address> createAddress(@RequestBody AddressRequest addressRequest) {
        Address saved = service.create(addressRequest);
        return ResponseEntity.created(UriComponentsBuilder.fromPath("/addresses/{id}")
                        .build("id", saved.getId()))
                .body(saved);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "get-address", summary = "Get Address By ID")
    ResponseEntity<Address> getAddress(@PathVariable("id") Long addressId) {
        return ResponseEntity.of(service.fetchById(addressId));
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @Operation(description = "update-address", summary = "Update Address By ID")
    ResponseEntity<Address> updateAddress(@PathVariable("id") Long addressId, @RequestBody AddressRequest addressRequest) {
        return ResponseEntity.of(service.updateById(addressId, addressRequest));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(description = "delete-address", summary = "Delete Address By ID")
    ResponseEntity<Void> deleteAddress(@PathVariable("id") Long addressId) {
        return service.deleteById(addressId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
