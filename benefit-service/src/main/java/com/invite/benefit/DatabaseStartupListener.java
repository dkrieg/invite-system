package com.invite.benefit;

import com.invite.benefit.entity.BenefitTierEntity;
import com.invite.benefit.repository.BenefitPackageRepository;
import com.invite.benefit.repository.BenefitRepository;
import com.invite.benefit.repository.BenefitTierRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequiredArgsConstructor
@Slf4j
class DatabaseStartupListener implements ApplicationListener<ContextRefreshedEvent> {
    BenefitTierRepository benefitTierRepository;
    BenefitPackageRepository benefitPackageRepository;
    BenefitRepository benefitRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        benefitPackageRepository.deleteAll();
        benefitRepository.deleteAll();
        benefitTierRepository.deleteAll();
        benefitTierRepository.saveAllAndFlush(List.of(
                new BenefitTierEntity("GOLD"),
                new BenefitTierEntity("SILVER"),
                new BenefitTierEntity("BRONZE")
        ));
    }
}