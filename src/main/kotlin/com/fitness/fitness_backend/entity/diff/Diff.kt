package com.fitness.fitness_backend.entity.diff

import com.fitness.fitness_backend.entity.Client

data class Diff(
    var timestamp: Int,
    var clients: List<Client>
)