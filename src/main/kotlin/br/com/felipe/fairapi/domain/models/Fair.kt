package br.com.felipe.fairapi.domain.models

import java.time.LocalDateTime

data class Fair (
    val id: Long? = null,
    val longitude: Double,
    val latitude: Double,
    val sector: String,
    val area: String,
    val districtCode: Long,
    val districtName: String,
    val subCityHallCode: Long,
    val subCityHallName: String,
    val region5: String,
    val region8: String,
    val name: String,
    val register: String,
    val street: String,
    val number: String,
    val neighborhood: String,
    val reference: String,
    val active: Boolean = true,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)