package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.repositories.FairRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.argumentCaptor
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class DeleteFairServiceTest {
    @Mock
    lateinit var repository: FairRepository

    @InjectMocks
    lateinit var deleteFairService: DeleteFairService

    @Test
    fun `should be delete a fair`() {
        Mockito.`when`(repository.findById(Mockito.anyLong())).thenReturn(mockFair())

        deleteFairService.execute(ArgumentMatchers.anyLong())

        val captor = argumentCaptor<Fair>()
        Mockito.verify(repository).save(captor.capture())

        Assertions.assertThat(captor.firstValue.active).isFalse
    }

    @Test
    fun `should be throw ResourceNotFoundException when try to update a fair that doesn't exist`() {
        Mockito.`when`(repository.findById(Mockito.anyLong())).thenReturn(null)

        Assertions.assertThatThrownBy {
            deleteFairService.execute(ArgumentMatchers.anyLong())
        }.isInstanceOf(EntityNotFoundException::class.java)
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