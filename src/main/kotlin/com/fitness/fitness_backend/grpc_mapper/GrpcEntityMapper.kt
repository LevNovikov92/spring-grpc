package com.fitness.fitness_backend.grpc_mapper

import com.fitness.fitness_backend.entity.Model
import com.google.protobuf.GeneratedMessageV3


interface GrpcEntityMapper<Entity : Model, Message : GeneratedMessageV3> :
        GrpcRequestMapper<Entity, Message>,
        GrpcResponseMapper<Entity, Message>