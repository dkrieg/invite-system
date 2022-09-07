package com.invite.organization.repository;

import com.invite.organization.entity.OrganizationSegmentEntity;
import com.invite.organization.entity.ProviderGroupTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationSegmentRepository extends JpaRepository<OrganizationSegmentEntity, String> {

}
