package br.com.felipe.fairapi.application.controllers.v1.response

import br.com.felipe.fairapi.domain.models.Fair
import java.time.LocalDateTime

data class FairResponse (
    val id: Long,
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
    val active: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
){
    constructor(fairModel: Fair): this(
        id = fairModel.id ?: 0,
        longitude = fairModel.longitude,
        latitude = fairModel.latitude,
        sector = fairModel.sector,
        area = fairModel.area,
        districtCode = fairModel.districtCode,
        districtName = fairModel.districtName,
        subCityHallCode = fairModel.subCityHallCode,
        subCityHallName = fairModel.subCityHallName,
        region5 = fairModel.region5,
        region8 = fairModel.region8,
        name = fairModel.name,
        register = fairModel.register,
        street = fairModel.street,
        number = fairModel.number,
        neighborhood = fairModel.neighborhood,
        reference = fairModel.reference,
        active = fairModel.active,
        createdAt = fairModel.createdAt,
        updatedAt = fairModel.updatedAt
    )
}