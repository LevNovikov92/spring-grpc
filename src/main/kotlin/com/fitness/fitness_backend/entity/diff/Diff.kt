package com.fitness.fitness_backend.entity.diff

import com.fitness.fitness_backend.entity.Client

data class Diff(
    val timestamp: Int,
    val clients: List<Client>
)