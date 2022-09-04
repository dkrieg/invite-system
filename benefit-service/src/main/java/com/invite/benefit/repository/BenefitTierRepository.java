package com.invite.benefit.repository;

import com.invite.benefit.entity.BenefitTierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitTierRepository extends JpaRepository<BenefitTierEntity, String> {
}
