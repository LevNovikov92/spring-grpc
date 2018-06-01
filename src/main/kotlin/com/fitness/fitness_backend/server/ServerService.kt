package com.fitness.fitness_backend.server

import com.fitness.fitness_backend.diff_service.DiffServiceImpl
import io.grpc.Server
import io.grpc.ServerBuilder
import java.io.IOException

class ServerService {

    private var server: Server? = null

    @Throws(IOException::class)
    fun run() {
        System.out.println(">>> Run Server")
        server = buildServer(ServerConfig(50051))
                .start()
                .apply {
                    System.out.println("Server started, listening on $port")
                    awaitTermination()
                }
    }

    private fun stop() {
        server?.shutdown()
    }

    private fun buildServer(config: ServerConfig): Server {
        return config.run {
            ServerBuilder.forPort(port)
                    .addService(DiffServiceImpl())
                    .build()
        }
    }
}

