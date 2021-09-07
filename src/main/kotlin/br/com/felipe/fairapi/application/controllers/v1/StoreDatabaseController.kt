package br.com.felipe.fairapi.application.controllers.v1

import br.com.felipe.fairapi.domain.services.StoreDatabaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/fairs")
class StoreDatabaseController(
    private val storeDatabaseService: StoreDatabaseService
) {

    @PostMapping("/import-from-file")
    @ResponseStatus(HttpStatus.CREATED)
    fun execute() {
        storeDatabaseService.execute()
    }
}