package com.fitness.fitness_backend.repository;

import com.fitness.fitness_backend.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Lev
 * 10.10.2016
 */

@Repository
public interface ClientRepository extends BaseRepository<Client, UUID> {

}
