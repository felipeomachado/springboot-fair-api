package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.models.SearchFairFilter
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.RuntimeException

class SearchFairService(
    private val repository: FairRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun searchById(id: Long): Fair {
        try {
            return repository.findById(id) ?: throw EntityNotFoundException("Fair with id $id not found!")
        } catch (ex: RuntimeException) {
            log.error(">>>> SearchFairService >>>> searchById >>>> Error: ${ex.message}")
            throw ex;
        }
    }

    fun searchByFilter(filter: SearchFairFilter, page: Int, size: Int): List<Fair> {
        try {
            return repository.findAll(filter, page, size)
        }catch (ex: RuntimeException) {
            log.error(">>>> SearchFairService >>>> searchByFilter >>>> Error: ${ex.message}")
            throw ex;
        }
    }
}