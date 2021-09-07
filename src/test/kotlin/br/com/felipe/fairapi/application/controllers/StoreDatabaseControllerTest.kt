package br.com.felipe.fairapi.application.controllers

import br.com.felipe.fairapi.application.controllers.v1.StoreDatabaseController
import br.com.felipe.fairapi.domain.services.StoreDatabaseService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [StoreDatabaseController::class])
@AutoConfigureMockMvc
internal class StoreDatabaseControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var storeDatabaseService: StoreDatabaseService


    @Test
    fun `should be import data from file to database`() {
        val request = MockMvcRequestBuilders.post( "/v1/fairs/import-from-file")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }
}