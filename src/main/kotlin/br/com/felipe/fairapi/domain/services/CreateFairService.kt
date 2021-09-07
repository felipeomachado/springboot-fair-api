package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateFairService (
    private val repository: FairRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun execute(fair: Fair): Fair {
        try {
            return repository.save(fair)
        } catch (ex: RuntimeException) {
            log.error(">>>> CreateFairService >>>> execute >>>> Error: ${ex.message}")
            throw ex;
        }

    }
}