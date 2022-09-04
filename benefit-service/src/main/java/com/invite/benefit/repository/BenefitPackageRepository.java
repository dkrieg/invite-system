package com.invite.benefit.repository;

import com.invite.benefit.entity.BenefitEntity;
import com.invite.benefit.entity.BenefitPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitPackageRepository extends JpaRepository<BenefitPackageEntity, Long> {
}
