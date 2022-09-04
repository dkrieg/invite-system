package com.invite.organization.repository;

import com.invite.organization.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
    List<OrganizationEntity> findAllByProviderGroup(Long providerGroupId);
}
