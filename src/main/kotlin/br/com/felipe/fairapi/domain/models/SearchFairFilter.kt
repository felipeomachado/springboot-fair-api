package br.com.felipe.fairapi.domain.models

data class SearchFairFilter(
    val district: String? = null,
    val region5: String? = null,
    val fairName: String? = null,
    val neighborhood: String? = null
)