package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.models.SearchFairFilter
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import java.lang.RuntimeException
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class SearchFairServiceTest {
    @Mock
    lateinit var repository: FairRepository

    @InjectMocks
    lateinit var searchFairService: SearchFairService

    @Test
    fun `should be list fairs`() {
        Mockito.`when`(repository.findAll(any(), any(), any())).thenReturn(mockFairList())

        val fairs = searchFairService.searchByFilter(SearchFairFilter(), 2, 10)

        Assertions.assertThat(fairs).isNotEmpty
        Assertions.assertThat(fairs.size).isEqualTo(3)
    }

    @Test
    fun `should be thrown a exception when try to list fairs`() {
        Mockito.`when`(repository.findAll(any(), any(), any())).thenThrow(RuntimeException())

        Assertions.assertThatThrownBy {
            searchFairService.searchByFilter(filter = SearchFairFilter(), page = 2, size = 10)
        }.isInstanceOf(RuntimeException::class.java)
    }

    @Test
    fun `should be find a fair by id`() {
        Mockito.`when`(repository.findById(any())).thenReturn(mockFair())

        val fair = searchFairService.searchById(any())

        Assertions.assertThat(fair.id).isNotNull
        Assertions.assertThat(fair.id).isEqualTo(7L)
    }

    @Test
    fun `should be a thrown a exception when id was not found`() {
        Mockito.`when`(repository.findById(any())).thenReturn(null)

        Assertions.assertThatThrownBy {
            searchFairService.searchById(any())
        }.isInstanceOf(EntityNotFoundException::class.java)
    }

    private fun mockFairList(): List<Fair> {
        return listOf(
            mockFair(),
            mockFair().copy(
                id = 15,
                name = "Feira 15"
            ),
            mockFair().copy(
                id = 17,
                name = "Feira 17"
            )
        )
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