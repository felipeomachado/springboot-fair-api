package br.com.felipe.fairapi.application.controllers.v1

import br.com.felipe.fairapi.domain.services.StoreDatabaseService
import br.com.felipe.fairapi.domain.services.UploadFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*


@RestController
@RequestMapping("/v1/fairs")
class StoreDatabaseController(
    private val storeDatabaseService: StoreDatabaseService,
    private val uploadFileService: UploadFileService
) {
    @Value("\${file.path}")
    lateinit var path: String

    @PostMapping("/import-from-file")
    @ResponseStatus(HttpStatus.CREATED)
    fun execute() {
        storeDatabaseService.execute()
    }

    @PostMapping("/upload-file",
        consumes = ["multipart/form-data"])
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadFile(@RequestParam multipartFile: MultipartFile) {
        val fileName = "${UUID.randomUUID()}.csv"
        val file = File("${path}/${fileName}")

        FileOutputStream(file).use { os -> os.write(multipartFile.bytes) }
        uploadFileService.execute(file)
    }
}