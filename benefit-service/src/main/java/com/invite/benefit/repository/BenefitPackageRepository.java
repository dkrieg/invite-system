package com.invite.benefit.repository;

import com.invite.benefit.entity.BenefitPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BenefitPackageRepository extends JpaRepository<BenefitPackageEntity, Long> {
    Optional<BenefitPackageEntity> findByName(String name);
}
