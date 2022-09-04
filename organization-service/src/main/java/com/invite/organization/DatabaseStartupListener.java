package com.invite.organization;

import com.invite.organization.entity.ProviderGroupEntity;
import com.invite.organization.repository.CommunityRepository;
import com.invite.organization.repository.MarketRepository;
import com.invite.organization.repository.OrganizationRepository;
import com.invite.organization.repository.ProviderGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
@Slf4j
class DatabaseStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    CommunityRepository communityRepository;
    MarketRepository marketRepository;
    OrganizationRepository organizationRepository;
    ProviderGroupRepository providerGroupRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        communityRepository.deleteAll();
        marketRepository.deleteAll();
        organizationRepository.deleteAll();
        providerGroupRepository.deleteAll();
        providerGroupRepository.save(ProviderGroupEntity.builder().name("Peach Tree City Group").build());
    }
}