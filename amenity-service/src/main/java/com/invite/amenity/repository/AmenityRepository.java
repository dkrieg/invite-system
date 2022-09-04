package com.invite.amenity.repository;

import com.invite.amenity.entity.AmenityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenityRepository extends JpaRepository<AmenityEntity, Long> {

    List<AmenityEntity> findAllByOrganizationId(Long organizationId);
}
