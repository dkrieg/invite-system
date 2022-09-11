package com.invite.benefit.mapper;

import com.invite.benefit.domain.BenefitPackage;
import com.invite.benefit.domain.BenefitPackageRequest;
import com.invite.benefit.entity.BenefitPackageEntity;
import com.invite.benefit.repository.BenefitRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BenefitPackageMapper {
    BenefitRepository repository;
    BenefitMapper mapper;

    public BenefitPackage toDomain(BenefitPackageEntity entity) {
        return BenefitPackage.builder()
                .id(entity.getId())
                .name(entity.getName())
                .organizationId(entity.getOrganizationId())
                .benefits(entity.getBenefits().stream().map(mapper::toDomain).collect(Collectors.toList()))
                .build();
    }

    public BenefitPackageEntity toEntity(BenefitPackageRequest request) {
        return toEntity(new BenefitPackageEntity(), request);
    }

    public BenefitPackageEntity toEntity(BenefitPackageEntity entity, BenefitPackageRequest request) {
        entity.setName(request.getName());
        entity.setBenefits(Set.copyOf(repository.findAllById(request.getBenefitsIds())));
        entity.setOrganizationId(request.getOrganizationId());
        return entity;
    }
}
