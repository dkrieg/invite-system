package com.invite.club.repository;

import com.invite.club.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<ClubEntity, Long> {

    Optional<ClubEntity> findByName(String name);
}
