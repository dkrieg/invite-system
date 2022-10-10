package com.invite.club.gateway;

import com.invite.community.domain.Community;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient("COMMUNITY-SERVICE")
public interface CommunityServiceGateway {
    @GetMapping(path = "/ids", produces = APPLICATION_JSON_VALUE)
    List<Community> getCommunitiesByIds(@RequestParam("id") List<Long> ids);
}
