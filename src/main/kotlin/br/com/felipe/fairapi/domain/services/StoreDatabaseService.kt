package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.repositories.UpdateDatabaseRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.RuntimeException

class StoreDatabaseService (
    private val updateDatabaseRepository: UpdateDatabaseRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun execute() {
        try {
            log.info(">>>> StoreDatabaseService >>>> execute >> Start!")

            updateDatabaseRepository.updateDatabase("DEINFO_AB_FEIRASLIVRES_2014.csv")

            log.info(">>>> StoreDatabaseService >>>> execute >> End!")
        }catch (ex: RuntimeException) {
            log.error(">>>> StoreDatabaseService >>>> execute >>>> Error: ${ex.message}")
            throw ex
        }
    }
}