package br.com.felipe.fairapi.application.controllers

import br.com.felipe.fairapi.application.controllers.v1.DeleteFairController
import br.com.felipe.fairapi.domain.services.DeleteFairService
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
@WebMvcTest(controllers = [DeleteFairController::class])
@AutoConfigureMockMvc
internal class DeleteFairControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var service: DeleteFairService

    @Test
    fun `should be delete a fair`() {
        val request = MockMvcRequestBuilders.delete("/v1/fairs/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)

        mvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isNoContent)

    }
}