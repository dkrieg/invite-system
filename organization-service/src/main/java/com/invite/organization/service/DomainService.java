package com.invite.organization.service;

import java.util.Collection;
import java.util.Optional;

public interface DomainService<S, T> {
    Collection<S> fetchAll();

    S create(T request);

    Optional<S> fetchById(Long id);

    Optional<S> updateById(Long id, T request);

    boolean deleteById(Long id);
}