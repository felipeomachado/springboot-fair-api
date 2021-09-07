package br.com.felipe.fairapi.domain.services

import br.com.felipe.fairapi.domain.repositories.UpdateDatabaseRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
internal class StoreDatabaseServiceTest {

    @Mock
    lateinit var updateDatabaseRepository: UpdateDatabaseRepository

    @InjectMocks
    lateinit var storeDatabaseService: StoreDatabaseService

    @Test
    fun `should be update storage`() {
        storeDatabaseService.execute()

        verify(updateDatabaseRepository, atLeastOnce()).updateDatabase()
    }

    @Test
    fun `should be thrown a exception when try to update storage`() {
        Mockito.`when`(updateDatabaseRepository.updateDatabase()).thenThrow(RuntimeException())

        Assertions.assertThatThrownBy {
            storeDatabaseService.execute()
        }.isInstanceOf(RuntimeException::class.java)
    }
}