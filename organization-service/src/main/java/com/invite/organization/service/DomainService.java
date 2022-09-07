package com.invite.organization.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public interface DomainService<S, T, I> {
    Collection<S> fetchAll();

    S create(T request);

    Optional<S> fetchById(I id);

    Optional<S> updateById(I id, T request);

    boolean deleteById(I id);
}
