package br.com.felipe.fairapi.application.controllers.v1

import br.com.felipe.fairapi.domain.services.DeleteFairService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/fairs")
class DeleteFairController(
    private val deleteFairService: DeleteFairService
) {

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun execute(@PathVariable id: Long) {
        deleteFairService.execute(id)
    }
}