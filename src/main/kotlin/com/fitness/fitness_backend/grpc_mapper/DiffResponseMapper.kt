package com.fitness.fitness_backend.grpc_mapper

import com.fitness.fitness_backend.diff.DiffResponse
import com.fitness.fitness_backend.entity.diff.Diff

class DiffResponseMapper : GrpcResponseMapper<Diff, DiffResponse> {
    private val clientMapper = ClientMapper()

    override fun to(entity: Diff): DiffResponse =
            entity.run {
                DiffResponse.getDefaultInstance().toBuilder()
                        .setTimestamp(timestamp)
                        .addAllClients(clients.map { clientMapper.to(it) })
                        .build()
            }
}