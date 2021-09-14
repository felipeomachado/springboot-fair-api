package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.repositories.MessagingRepository
import br.com.felipe.fairapi.domain.repositories.UploadFileRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Files

class UploadFileService (
    private val uploadFileRepository: UploadFileRepository,
    private val messagingRepository: MessagingRepository
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun execute(file: File) {
        try {
            uploadFileRepository.uploadFile(file)
            messagingRepository.sendMessage(file.name)
        }catch (ex: RuntimeException) {
            log.error(">>>> UploadFileService >>>> execute >>>> Error: ${ex.message}")
            throw ex
        }finally {
            if (file.exists())
                file.delete()
        }
    }
}