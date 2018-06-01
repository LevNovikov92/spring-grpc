package com.fitness.fitness_backend.entity;

import java.util.UUID;

/**
 * Lev
 * 15.10.2016
 */
public interface Model {

    Long getChanged();

    void setChanged(Long changed);

    UUID getId();

    void setId(UUID id);
}
