package com.invite.benefit.repository;

import com.invite.benefit.entity.BenefitPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitPackageRepository extends JpaRepository<BenefitPackageEntity, Long> {
    List<BenefitPackageEntity> findAllByOrganizationId(Long organizationId);
}
