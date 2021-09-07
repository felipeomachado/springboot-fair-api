package br.com.felipe.fairapi.application.controllers.v1

import br.com.felipe.fairapi.application.controllers.v1.request.CreateFairRequest
import br.com.felipe.fairapi.application.controllers.v1.response.FairResponse
import br.com.felipe.fairapi.domain.services.CreateFairService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/fairs")
class CreateFairController(
    private val serviceCreate: CreateFairService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: CreateFairRequest): FairResponse {
        val fairCreated = serviceCreate.execute(request.toModel())
        return FairResponse(fairCreated)
    }
}