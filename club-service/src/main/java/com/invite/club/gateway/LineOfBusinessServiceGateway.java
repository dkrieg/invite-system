package com.invite.club.gateway;

import com.invite.line.domain.LineOfBusiness;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("LINE-OF-BUSINESS-SERVICE")
public interface LineOfBusinessServiceGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    LineOfBusiness getLineOfBusiness(@RequestParam("id") Long id);
}
