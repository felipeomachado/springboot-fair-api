package br.com.felipe.fairapi.application.controllers.v1.request

import br.com.felipe.fairapi.domain.models.Fair
import javax.validation.constraints.NotBlank

data class CreateFairRequest (
    val longitude: Double,
    val latitude: Double,
    @field:NotBlank
    val sector: String,
    @field:NotBlank
    val area: String,
    val districtCode: Long,
    @field:NotBlank
    val districtName: String,
    val subCityHallCode: Long,
    @field:NotBlank
    val subCityHallName: String,
    @field:NotBlank
    val region5: String,
    @field:NotBlank
    val region8: String,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val register: String,
    @field:NotBlank
    val street: String,
    @field:NotBlank
    val number: String,
    @field:NotBlank
    val neighborhood: String,
    @field:NotBlank
    val reference: String,
){
    fun toModel() : Fair {
        return Fair(
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