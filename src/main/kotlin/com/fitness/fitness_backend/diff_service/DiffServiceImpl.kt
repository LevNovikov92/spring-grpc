package com.fitness.fitness_backend.diff_service

import com.fitness.fitness_backend.diff.DiffGrpc
import com.fitness.fitness_backend.diff.DiffRequest
import com.fitness.fitness_backend.diff.DiffResponse
import io.grpc.stub.StreamObserver

class DiffServiceImpl
    : DiffGrpc.DiffImplBase() {

    override fun getDiff(request: DiffRequest, responseObserver: StreamObserver<DiffResponse>) {
        val response = DiffResponse.newBuilder()
                .setTimestamp(request.timestamp + 100)
                .addClients(request.getClients(0))
                .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}