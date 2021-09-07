package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.RuntimeException
import java.time.LocalDateTime

class UpdateFairService (
    private val repository: FairRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun execute(id: Long, fair: Fair): Fair {
        try {
            return repository.update(id, fair.copy(updatedAt = LocalDateTime.now()))
        }catch (ex: RuntimeException) {
            log.error(">>>> UpdateFairService >>>> execute >>>> Error: ${ex.message}")
            throw ex;
        }
    }
}