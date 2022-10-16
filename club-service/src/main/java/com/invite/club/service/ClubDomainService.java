package com.invite.club.service;

import com.invite.club.domain.Club;
import com.invite.club.domain.ClubRequest;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ClubDomainService extends DomainService<Club, ClubRequest, Long> {
    Optional<Club> fetchByName(String name);
}
