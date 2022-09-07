package com.invite.benefit.repository;

import com.invite.benefit.entity.BenefitTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitTypeRepository extends JpaRepository<BenefitTypeEntity, String> {
}
