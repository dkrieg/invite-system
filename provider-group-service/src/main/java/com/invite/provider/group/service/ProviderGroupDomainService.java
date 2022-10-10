package com.invite.provider.group.service;

import com.invite.domain.service.DomainService;
import com.invite.provider.group.domain.ProviderGroup;
import com.invite.provider.group.domain.ProviderGroupRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public interface ProviderGroupDomainService extends DomainService<ProviderGroup, ProviderGroupRequest, Long> {
    Collection<ProviderGroup> fetchAllById(List<Long> ids);
}
