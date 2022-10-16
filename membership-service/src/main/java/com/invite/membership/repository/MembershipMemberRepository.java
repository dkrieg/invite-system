package com.invite.membership.repository;

import com.invite.membership.entity.MembershipMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipMemberRepository extends JpaRepository<MembershipMemberEntity, Long> {
}
