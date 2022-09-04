package com.invite.benefit.mapper;

import com.invite.benefit.domain.Benefit;
import com.invite.benefit.domain.BenefitPackage;
import com.invite.benefit.domain.BenefitPackageRequest;
import com.invite.benefit.domain.BenefitRequest;
import com.invite.benefit.entity.BenefitEntity;
import com.invite.benefit.entity.BenefitPackageEntity;
import com.invite.benefit.repository.BenefitRepository;
import com.invite.benefit.repository.BenefitTierRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitPackageMapper {
    BenefitRepository repository;

    public BenefitPackage toDomain(BenefitPackageEntity entity) {
        return BenefitPackage.builder()
                .id(entity.getId())
                .name(entity.getName())
                .benefitsIds(entity.getBenefits().stream().map(BenefitEntity::getId).collect(Collectors.toList()))
                .build();
    }

    public BenefitPackageEntity toEntity(BenefitPackageRequest request) {
        return toEntity(new BenefitPackageEntity(), request);
    }

    public BenefitPackageEntity toEntity(BenefitPackageEntity entity, BenefitPackageRequest request) {
        entity.setName(request.getName());
        entity.setBenefits(Set.copyOf(repository.findAllById(request.getBenefitsIds())));
        return entity;
    }
}
