package com.fitness.fitness_backend.diff_service

import com.fitness.fitness_backend.diff.DiffGrpc
import com.fitness.fitness_backend.diff.DiffRequest
import com.fitness.fitness_backend.diff.DiffResponse
import com.fitness.fitness_backend.entity.Client
import com.fitness.fitness_backend.entity.diff.Diff
import com.fitness.fitness_backend.grpc_mapper.DiffResponseMapper
import io.grpc.stub.StreamObserver

class DiffServiceImpl(
        private val diffResponseMapper: DiffResponseMapper = DiffResponseMapper()
)
    : DiffGrpc.DiffImplBase() {

    override fun getDiff(request: DiffRequest, responseObserver: StreamObserver<DiffResponse>) {
        val diff = Diff(
                123,
                listOf(Client().apply { name = "Name1" }))

        responseObserver.onNext(diffResponseMapper.to(diff))
        responseObserver.onCompleted()
    }
}