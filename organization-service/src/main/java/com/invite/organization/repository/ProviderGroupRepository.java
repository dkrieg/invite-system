package com.invite.organization.repository;

import com.invite.organization.entity.ProviderGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderGroupRepository extends JpaRepository<ProviderGroupEntity, Long> {
    Optional<ProviderGroupEntity> findByName(String name);
}
