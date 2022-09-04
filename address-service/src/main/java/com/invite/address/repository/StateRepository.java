package com.invite.address.repository;

import com.invite.address.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, String> {
}
