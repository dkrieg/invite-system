package com.invite.club.gateway;

import com.invite.address.domain.Address;
import com.invite.address.domain.AddressRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("ADDRESS-SERVICE")
public interface AddressServiceGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Address getAddress(@PathVariable("id") Long id);

    @PostMapping(path = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Address createAddress(AddressRequest request);

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Address updateAddress(@PathVariable("id") Long addressId, AddressRequest request);

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    void deleteAddress(@PathVariable("id") Long addressId);
}
