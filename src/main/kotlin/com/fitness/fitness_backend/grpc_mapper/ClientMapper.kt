package com.fitness.fitness_backend.grpc_mapper

import com.fitness.fitness_backend.entity.Client


class ClientMapper : GrpcEntityMapper<Client, com.fitness.fitness_backend.diff.Client> {
    override fun to(entity: Client): com.fitness.fitness_backend.diff.Client =
            com.fitness.fitness_backend.diff.Client.getDefaultInstance().toBuilder()
                    .setName(entity.name)
                    .build()

    override fun from(message: com.fitness.fitness_backend.diff.Client): Client =
            Client().apply {
                name = message.name
            }

}