package com.fitness.fitness_backend.diff_service

import com.fitness.fitness_backend.diff.DiffGrpc
import com.fitness.fitness_backend.diff.DiffRequest
import com.fitness.fitness_backend.diff.DiffResponse
import com.fitness.fitness_backend.entity.Client
import com.fitness.fitness_backend.entity.Model
import com.fitness.fitness_backend.entity.diff.Diff
import com.fitness.fitness_backend.grpc_mapper.DiffResponseMapper
import com.fitness.fitness_backend.repository.BaseRepository
import com.fitness.fitness_backend.repository.ClientRepository
import com.fitness.fitness_backend.utils.DateUtils
import io.grpc.stub.StreamObserver
import org.springframework.context.NoSuchMessageException
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

class DiffServiceImpl(
        private val clientRepository: ClientRepository,
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
//
//    fun synchronize(diff: Diff): Diff {
//        val newTimestamp = DateUtils.getUnixTimestamp()
//        syncEntities(diff.clients)
//
//        val lastSyncTimestamp = diff.timestamp
//        return getDiff(lastSyncTimestamp, newTimestamp)
//    }
//
//    private fun getDiff(timestamp: Long?, newTimestamp: Long): Diff {
//        //        diff.setUser(getDiffByEntity(new User(), companyId, timestamp));
//        return Diff(newTimestamp, getDiffByEntity(Client(), timestamp))
//    }
//
//    private fun <T> getDiffByEntity(entity: T, timestamp: Long?): List<T> {  //TODO refactoring
//        val repository = getRepositoryByEntity(entity)
//        return repository.findByChangedGreaterThan(timestamp)
//    }
//
//    private fun <T : Model> syncEntities(entities: List<T>?) {
//        var repository: JpaRepository<T, UUID>? = null
//        if (entities == null) {
//            throw NoSuchMessageException("Wrong request format")
//        }
//        for (entity in entities) {
//            if (repository == null) {
//                repository = getRepositoryByEntity(entity)
//            }
//
//            val id = entity.id
//            val existedEntity = repository.findById(id) as Model
//            if (entity.changed < existedEntity.changed) {
//                continue
//            }
//            repository.save(entity)
//        }
//    }
//
//    private fun <T> getRepositoryByEntity(entity: T): BaseRepository<T, UUID> {
//        if (entity is Client) {
//            return this.clientRepository as BaseRepository<T, UUID>
//        }
//
//        throw NoSuchMessageException(String.format("Repository not found for %s class", entity.javaClass.toString()))
//    }
}