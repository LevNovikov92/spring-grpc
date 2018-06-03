package com.fitness.fitness_backend.diff_service

import com.fitness.fitness_backend.diff.DiffGrpc
import com.fitness.fitness_backend.diff.DiffRequest
import com.fitness.fitness_backend.diff.DiffResponse
import com.fitness.fitness_backend.entity.Client
import com.fitness.fitness_backend.entity.Model
import com.fitness.fitness_backend.entity.diff.Diff
import com.fitness.fitness_backend.grpc_mapper.DiffRequestMapper
import com.fitness.fitness_backend.grpc_mapper.DiffResponseMapper
import com.fitness.fitness_backend.repository.BaseRepository
import com.fitness.fitness_backend.repository.ClientRepository
import com.fitness.fitness_backend.utils.DateUtils
import io.grpc.stub.StreamObserver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.NoSuchMessageException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
class DiffServiceImpl(
        private val diffResponseMapper: DiffResponseMapper = DiffResponseMapper(),
        private val diffRequestMapper: DiffRequestMapper = DiffRequestMapper()
) : DiffGrpc.DiffImplBase() {

    @Autowired
    lateinit var clientRepository: ClientRepository

    override fun getDiff(request: DiffRequest, responseObserver: StreamObserver<DiffResponse>) {
        clientRepository.findAll()
        responseObserver.onNext(
                diffResponseMapper.to(
                        synchronize(
                                diffRequestMapper.from(request))))
        responseObserver.onCompleted()
    }

    fun synchronize(diff: Diff): Diff {
        val newTimestamp = DateUtils.getUnixTimestamp()
        syncEntities(diff.clients)

        val lastSyncTimestamp = diff.timestamp
        return getDiff(lastSyncTimestamp, newTimestamp)
    }

    private fun getDiff(timestamp: Long?, newTimestamp: Long): Diff {
        return Diff(newTimestamp, getDiffByEntity(Client(), timestamp))
    }

    private fun <T> getDiffByEntity(entity: T, timestamp: Long?): List<T> {  //TODO refactoring
        val repository = getRepositoryByEntity(entity)
        return repository.findByChangedGreaterThan(timestamp)
    }

    private fun <T : Model> syncEntities(entities: List<T>?) {
        var repository: JpaRepository<T, UUID>? = null
        if (entities == null) {
            throw NoSuchMessageException("Wrong request format")
        }
        for (entity in entities) {
            if (repository == null) {
                repository = getRepositoryByEntity(entity)
            }

            val existedEntity = repository.findById(entity.id)
            if (!existedEntity.isPresent) continue
            if (entity.changed < existedEntity.get().changed) {
                continue
            }
            repository.save(entity)
        }
    }

    private fun <T> getRepositoryByEntity(entity: T): BaseRepository<T, UUID> {
        if (entity is Client) {
            return this.clientRepository as BaseRepository<T, UUID>
        }

        throw NoSuchMessageException("Repository not found for $entity class")
    }
}