package br.com.felipe.fairapi.application.controllers.v1.request

import br.com.felipe.fairapi.domain.models.Fair

data class UpdateFairRequest(
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
) {
    fun toModel() : Fair {
        return Fair(
            id = null,
            longitude = this.longitude,
            latitude = this.latitude,
            sector = this.sector,
            area = this.area,
            districtCode = this.districtCode,
            districtName = this.districtName,
            subCityHallCode = this.subCityHallCode,
            subCityHallName = this.subCityHallName,
            region5 = this.region5,
            region8 = this.region8,
            name = this.name,
            register = this.register,
            street = this.street,
            number = this.number,
            reference = this.reference,
            neighborhood = this.neighborhood
        )
    }

}