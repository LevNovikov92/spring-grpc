package com.fitness.fitness_backend.entity.diff

import com.fitness.fitness_backend.entity.Client

data class Diff(
    var timestamp: Long,
    var clients: List<Client>
)