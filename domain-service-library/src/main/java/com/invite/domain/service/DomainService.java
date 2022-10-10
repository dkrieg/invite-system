package com.invite.domain.service;

import java.util.Collection;
import java.util.Optional;

public interface DomainService<S, T, I> {
    Collection<S> fetchAll();

    S create(T request);

    Optional<S> fetchById(I id);

    Optional<S> updateById(I id, T request);

    boolean deleteById(I id);
}
