package com.invite.amenity.service;

import java.util.Collection;
import java.util.Optional;

public interface DomainService<S, T, I> {
    Collection<S> fetchAll();

    S create(T request);

    Optional<S> fetchById(I id);

    default Optional<S> updateById(I id, T request) {
        throw new UnsupportedOperationException();
    }

    boolean deleteById(I id);
}
