package com.invite.provider.group.repository;

import com.invite.provider.group.entity.ProviderGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderGroupRepository extends JpaRepository<ProviderGroupEntity, Long> {
    Optional<ProviderGroupEntity> findByName(String name);
}
