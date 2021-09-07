package br.com.felipe.fairapi.application.controllers.v1.request

import br.com.felipe.fairapi.domain.models.SearchFairFilter

data class SearchFairFilterRequest(
    val district: String? = null,
    val region05: String? = null,
    val fairName: String? = null,
    val neighborhood: String? = null
){
    fun toModel(): SearchFairFilter {
        return SearchFairFilter(
            district = this.district,
            region5 = this.region05,
            fairName = this.fairName,
            neighborhood = this.neighborhood
        )
    }
}