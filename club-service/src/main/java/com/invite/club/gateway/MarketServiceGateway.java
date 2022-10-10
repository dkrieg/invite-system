package com.invite.club.gateway;

import com.invite.market.domain.Market;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("MARKET-SERVICE")
public interface MarketServiceGateway {
    @GetMapping(path = "/ids", produces = APPLICATION_JSON_VALUE)
    List<Market> getMarketsByIds(@RequestParam("id") List<Long> ids);
}
