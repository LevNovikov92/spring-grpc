package com.fitness.fitness_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Lev
 * 08.12.2016
 */
public interface BaseRepository<Entity, Id> extends JpaRepository<Entity, Id> {

    List<Entity> findByChangedGreaterThan(Long timestamp);
}
