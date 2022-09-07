package com.invite.membership.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public interface DomainService<S, T, I> {
    Collection<S> fetchAll();

    S create(T request);

    Optional<S> fetchById(I id);

    default Optional<S> updateById(I id, T request) {
        throw new UnsupportedOperationException();
    }

    boolean deleteById(I id);
}
