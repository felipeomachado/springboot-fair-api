package br.com.felipe.fairapi.application.controllers.v1

import br.com.felipe.fairapi.application.controllers.v1.request.SearchFairFilterRequest
import br.com.felipe.fairapi.application.controllers.v1.response.FairResponse
import br.com.felipe.fairapi.domain.services.SearchFairService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/fairs")
class SearchFairController(
    private val searchFairService: SearchFairService
) {
    @GetMapping
    fun execute(searchFairFilterRequest: SearchFairFilterRequest, pageable: Pageable): Page<FairResponse> {
        val fairs = searchFairService.searchByFilter(
            filter = searchFairFilterRequest.toModel(),
            page = pageable.pageNumber,
            size = pageable.pageSize
        )
        val fairListResponse = fairs.map { FairResponse(it) }.toList()
        return PageImpl(fairListResponse)
    }

    @GetMapping("/{id}")
    fun execute(@PathVariable id: Long): FairResponse {
        return FairResponse(searchFairService.searchById(id))
    }
}