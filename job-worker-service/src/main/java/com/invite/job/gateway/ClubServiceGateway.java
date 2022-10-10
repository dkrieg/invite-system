package com.invite.job.gateway;

import com.invite.club.domain.Club;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("CLUB-SERVICE")
public interface ClubServiceGateway {
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    Club getClub(@PathVariable("id") Long clubId);
}
