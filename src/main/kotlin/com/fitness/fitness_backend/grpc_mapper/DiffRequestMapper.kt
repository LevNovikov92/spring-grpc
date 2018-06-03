package com.fitness.fitness_backend.grpc_mapper

import com.fitness.fitness_backend.diff.DiffRequest
import com.fitness.fitness_backend.entity.diff.Diff


class DiffRequestMapper : GrpcRequestMapper<Diff, DiffRequest> {

    private val clientMapper = ClientMapper()

    override fun from(message: DiffRequest): Diff =
            message.run {
                Diff(
                        0, //TODO re
                        clientsList.map { clientMapper.from(it) })
            }
}