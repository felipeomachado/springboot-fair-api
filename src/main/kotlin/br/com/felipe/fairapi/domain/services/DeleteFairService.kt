package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.RuntimeException

class DeleteFairService (
    private val repository: FairRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun execute(id: Long) {
        try {
            val fair = repository.findById(id) ?: throw EntityNotFoundException("Fair with id: $id not found!")
            repository.save(fair.copy(active = false))
        } catch (ex: RuntimeException) {
            log.error(">>>> DeleteFairService >>>> execute >>>> Error: ${ex.message}")
            throw ex;
        }
    }
}