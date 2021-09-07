package br.com.felipe.fairapi.application.controllers.v1

import br.com.felipe.fairapi.application.controllers.v1.request.UpdateFairRequest
import br.com.felipe.fairapi.application.controllers.v1.response.FairResponse
import br.com.felipe.fairapi.domain.services.UpdateFairService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/fairs")
class UpdateFairController(
    private val updateFairService: UpdateFairService
) {

    @PutMapping("/{id}")
    fun execute(@PathVariable id: Long, @RequestBody updateFairRequest: UpdateFairRequest): FairResponse {
        val fairUpdated = updateFairService.execute(
            id,
            updateFairRequest.toModel()
        )
        return FairResponse(fairUpdated)
    }
}