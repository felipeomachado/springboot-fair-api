package br.com.felipe.fairapi.infra.models

import br.com.felipe.fairapi.domain.models.Fair
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "fairs")
data class FairEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    val active: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
){
    constructor(fairModel: Fair): this(
        id = fairModel.id,
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
        active = fairModel.active
    )

    fun toModel(): Fair {
        return (
                Fair(
                    id = this.id,
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
                    neighborhood = this.neighborhood,
                    reference = this.reference
                )
                )
    }
}
