package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class UpdateFairServiceTest {
    @Mock
    lateinit var repository: FairRepository

    @InjectMocks
    lateinit var updateFairService: UpdateFairService

    @Test
    fun `should be update a fair`() {
        val expectedDistrictCode = 2L
        val expectedDistrictName = "Distrito Atualizado"
        val expectedLongitude = -3.784596

        val fairUpdateReturn = mockFair().copy(districtCode = expectedDistrictCode, districtName = expectedDistrictName, longitude = expectedLongitude)

        Mockito.`when`(repository.update(any(), any())).thenReturn(fairUpdateReturn)

        val fair = updateFairService.execute(id = 7, fair = mockFair())

        Assertions.assertThat(fair).isNotNull
        Assertions.assertThat(fair.id).isEqualTo(7)
        Assertions.assertThat(fair.districtCode).isEqualTo(expectedDistrictCode)
        Assertions.assertThat(fair.districtName).isEqualTo(expectedDistrictName)
        Assertions.assertThat(fair.longitude).isEqualTo(expectedLongitude)
    }

    @Test
    fun `should be thrown a exception when try to update a fair`() {
        Mockito.`when`(repository.update(any(), any())).thenThrow(RuntimeException())

        Assertions.assertThatThrownBy {
            updateFairService.execute(id = 7, fair = mockFair())
        }.isInstanceOf(RuntimeException::class.java)
    }

    private fun mockFair() : Fair {
        return Fair(
            id = 7,
            longitude = -3.784596,
            latitude = 5.4178495,
            sector = "test sector",
            area = "test area",
            districtCode = 1,
            districtName = "Bacabal",
            subCityHallCode = 2,
            subCityHallName = "Centro",
            region5 = "Leste",
            region8 = "Norte",
            name = "Feira da Cohab",
            register = "4041-0",
            street = "RUA ANTONIO LOBO",
            number = "626",
            neighborhood = "COHAB",
            reference = "PROX AO HOSPITAL",
            active = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}