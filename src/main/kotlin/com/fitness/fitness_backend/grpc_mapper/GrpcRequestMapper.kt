package com.fitness.fitness_backend.grpc_mapper

import com.google.protobuf.GeneratedMessageV3

interface GrpcRequestMapper<out Entity, in Message : GeneratedMessageV3> {

    fun from(message: Message): Entity
}