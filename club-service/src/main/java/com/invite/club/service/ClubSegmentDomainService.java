package com.invite.club.service;

import com.invite.club.domain.ClubSegment;
import com.invite.club.domain.ClubSegmentRequest;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ClubSegmentDomainService extends DomainService<ClubSegment, ClubSegmentRequest, String> {

}
