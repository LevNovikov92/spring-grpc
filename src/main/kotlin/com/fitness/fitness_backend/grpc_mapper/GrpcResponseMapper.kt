package com.fitness.fitness_backend.grpc_mapper

import com.google.protobuf.GeneratedMessageV3

interface GrpcResponseMapper<in Entity, out Message : GeneratedMessageV3> {

    fun to(entity: Entity): Message
}