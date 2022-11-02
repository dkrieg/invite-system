package com.invite.job.gateway;

import com.invite.address.domain.Distance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ADDRESS-SERVICE")
public interface AddressServiceGateway {
    @GetMapping(path = "/calculate-distance")
    Distance calculateDistance(@RequestParam("start") Long startId, @RequestParam("end") Long endId);
}
