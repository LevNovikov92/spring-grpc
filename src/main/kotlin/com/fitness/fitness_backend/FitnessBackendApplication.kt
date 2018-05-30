package com.fitness.fitness_backend

import com.fitness.fitness_backend.server.ServerService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FitnessBackendApplication {

    @Bean(initMethod = "run")
    fun serverService() = ServerService()
}

fun main(args: Array<String>) {
    runApplication<FitnessBackendApplication>(*args)
}
