package com.invite.community.service;

import com.invite.community.domain.Community;
import com.invite.community.domain.CommunityRequest;
import com.invite.domain.service.DomainService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public interface CommunityDomainService extends DomainService<Community, CommunityRequest, Long> {
    Collection<Community> fetchAllById(List<Long> ids);
}
