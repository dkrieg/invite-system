package com.invite.club.gateway;

import com.invite.provider.group.domain.ProviderGroup;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("PROVIDER-GROUP-SERVICE")
public interface ProviderGroupServiceGateway {
    @GetMapping(path = "/ids", produces = APPLICATION_JSON_VALUE)
    List<ProviderGroup> getProviderGroupsByIds(@RequestParam("id") List<Long> ids);
}
