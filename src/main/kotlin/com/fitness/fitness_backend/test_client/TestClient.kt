package com.fitness.fitness_backend.test_client

import com.fitness.fitness_backend.diff.DiffGrpc
import com.fitness.fitness_backend.diff.DiffRequest
import com.fitness.fitness_backend.diff.DiffResponse
import io.grpc.Channel
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    var channel: ManagedChannel? = null
    try {
        val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build()
        val client = Client(channel)
        client.greet("Lev")
    } finally {
        channel?.shutdown()?.awaitTermination(5, TimeUnit.SECONDS)
    }
}

class Client(channel: Channel) {

    private val blockingStub: DiffGrpc.DiffBlockingStub
            = DiffGrpc.newBlockingStub(channel)

    fun greet(name: String) {
        val request = DiffRequest.newBuilder()
                .setTimestamp(100)
                .build()
        val response: DiffResponse =  try {
            blockingStub.getDiff(request)
        } catch (e: StatusRuntimeException) {
            System.out.println("RPC failed: ${e.status}")
            return
        }

        System.out.println("Greeting: $response")
    }

    fun shutdown() {

    }
}