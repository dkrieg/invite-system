package com.invite.membership.repository;

import com.invite.membership.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    Optional<MembershipEntity> findByMembersId(Long memberId);
}
