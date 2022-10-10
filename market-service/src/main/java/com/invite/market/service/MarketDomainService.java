package com.invite.market.service;

import com.invite.domain.service.DomainService;
import com.invite.market.domain.Market;
import com.invite.market.domain.MarketRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public interface MarketDomainService extends DomainService<Market, MarketRequest, Long> {
    Collection<Market> fetchAllById(List<Long> ids);
}
