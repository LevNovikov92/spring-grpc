package com.fitness.fitness_backend.test_client

import com.fitness.fitness_backend.diff.Client
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
        val client = TestClient(channel)
        client.test()
    } finally {
        channel?.shutdown()?.awaitTermination(5, TimeUnit.SECONDS)
    }
}

class TestClient(channel: Channel) {

    private val blockingStub: DiffGrpc.DiffBlockingStub
            = DiffGrpc.newBlockingStub(channel)

    fun test() {
        val request = DiffRequest.newBuilder()
                .setTimestamp(100)
                .addClients(Client.getDefaultInstance().toBuilder().setName("Valera"))
                .build()
        val response: DiffResponse =  try {
            blockingStub.getDiff(request)
        } catch (e: StatusRuntimeException) {
            System.out.println("RPC failed: ${e.status}")
            return
        }

    }

    fun shutdown() {

    }
}