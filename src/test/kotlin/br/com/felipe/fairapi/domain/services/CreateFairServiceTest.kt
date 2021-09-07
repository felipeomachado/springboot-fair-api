package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class CreateFairServiceTest {
    @Mock
    lateinit var repository: FairRepository

    @InjectMocks
    lateinit var createFairService: CreateFairService

    @Test
    fun `should be create a new fair`() {
        val fairToReturn = mockFair()

        Mockito.`when`(repository.save(any())).thenReturn(fairToReturn)

        val fairToSave = fairToReturn.copy(id = null)
        val fairSaved = createFairService.execute(fairToSave)

        Assertions.assertThat(fairSaved).isNotNull
        Assertions.assertThat(fairSaved.id).isEqualTo(7);
    }

    @Test
    fun `should be thrown a exception on try to create a new fair`() {
        Mockito.`when`(repository.save(any())).thenThrow(RuntimeException())

        Assertions.assertThatThrownBy {
            createFairService.execute(mockFair())
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
            reference = "PROX AO HOSPITAL"
        )
    }
}