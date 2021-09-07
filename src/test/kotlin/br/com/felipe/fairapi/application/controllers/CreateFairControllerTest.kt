package br.com.felipe.fairapi.application.controllers

import br.com.felipe.fairapi.application.controllers.v1.CreateFairController
import br.com.felipe.fairapi.application.controllers.v1.request.CreateFairRequest
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.services.CreateFairService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [CreateFairController::class])
@AutoConfigureMockMvc
internal class CreateFairControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var createFairService: CreateFairService

    @Test
    fun `should be create a fair`() {

        val fairResponse = mockFairResponse()
        val json = ObjectMapper().writeValueAsString(mockCreateFairRequest())

        BDDMockito.given(createFairService.execute(any())).willReturn(fairResponse)

        val request = MockMvcRequestBuilders.post("/v1/fairs")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)

        mvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("name").value(fairResponse.name))
            .andExpect(MockMvcResultMatchers.jsonPath("districtName").value(fairResponse.districtName))
            .andExpect(MockMvcResultMatchers.jsonPath("region8").value(fairResponse.region8))
    }

    private fun mockCreateFairRequest(): CreateFairRequest {
        return CreateFairRequest(
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

    private fun mockFairResponse(): Fair {
        return Fair(
            id= 1,
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
            active = true,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}