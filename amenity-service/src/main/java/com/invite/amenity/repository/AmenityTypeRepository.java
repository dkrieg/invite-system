package com.invite.amenity.repository;

import com.invite.amenity.entity.AmenityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityTypeRepository extends JpaRepository<AmenityTypeEntity, String> {
}
