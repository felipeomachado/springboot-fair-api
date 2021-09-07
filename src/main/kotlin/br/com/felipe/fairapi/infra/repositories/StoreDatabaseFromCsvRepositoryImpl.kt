package br.com.felipe.fairapi.infra.repositories

import br.com.felipe.fairapi.domain.repositories.UpdateDatabaseRepository
import br.com.felipe.fairapi.infra.models.FairEntity
import br.com.felipe.fairapi.infra.repositories.spring.jpa.FairJpaRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StoreDatabaseFromCsvRepositoryImpl(
    private val fairJpaRepository: FairJpaRepository
) : UpdateDatabaseRepository {
    override fun updateDatabase() {
        val fairs = mutableListOf<FairEntity>()

        csvReader(init = {
            skipMissMatchedRow = true
        })
            .open("src/main/resources/files/DEINFO_AB_FEIRASLIVRES_2014.csv") {
                readAllWithHeaderAsSequence().forEach { row: Map<String, String> ->
                    val fairEntity = FairEntity(
                        id = row["ID"]?.toLong(),
                        longitude = row["LONG"]?.toDouble() ?: 0.0,
                        latitude = row["LAT"]?.toDouble() ?: 0.0,
                        sector = row["SETCENS"] ?: "",
                        area = row["AREAP"] ?: "",
                        districtCode = row["CODDIST"]?.toLong() ?: 0,
                        districtName = row["DISTRITO"] ?: "",
                        subCityHallCode = row["CODSUBPREF"]?.toLong() ?: 0,
                        subCityHallName = row["SUBPREFE"] ?: "",
                        region5 = row["REGIAO5"] ?: "",
                        region8 = row["REGIAO8"] ?: "",
                        name = row["NOME_FEIRA"] ?: "",
                        register = row["REGISTRO"] ?: "",
                        street = row["LOGRADOURO"] ?: "",
                        number = row["NUMERO"] ?: "",
                        neighborhood = row["BAIRRO"] ?: "",
                        reference = row["BAIRRO"] ?: "",
                        active = true
                    )

                    fairEntity.id?.let {
                        val optionalFair = fairJpaRepository.findById(it)

                        if (optionalFair.isPresent)
                            fairJpaRepository.save(optionalFair.get().copy(
                                longitude = fairEntity.longitude,
                                latitude = fairEntity.latitude,
                                sector = fairEntity.sector,
                                area = fairEntity.area,
                                districtCode = fairEntity.districtCode,
                                districtName = fairEntity.districtName,
                                subCityHallCode = fairEntity.subCityHallCode,
                                subCityHallName = fairEntity.subCityHallName,
                                region5 = fairEntity.region5,
                                region8 = fairEntity.region8,
                                name = fairEntity.name,
                                register = fairEntity.register,
                                street = fairEntity.street,
                                number = fairEntity.number,
                                neighborhood = fairEntity.neighborhood,
                                reference = fairEntity.reference,
                                updatedAt = LocalDateTime.now()))
                        else
                            fairs.add(fairEntity)

                    }
                }
            }

        fairJpaRepository.saveAll(fairs)
    }


}