package com.invite.benefit.repository;

import com.invite.benefit.entity.BenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitRepository extends JpaRepository<BenefitEntity, Long> {
    List<BenefitEntity> findAllByOrganizationId(Long organizationId);
}
