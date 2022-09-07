package com.invite.membership.repository;

import com.invite.membership.entity.MembershipLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipLevelRepository extends JpaRepository<MembershipLevelEntity, String> {
}
