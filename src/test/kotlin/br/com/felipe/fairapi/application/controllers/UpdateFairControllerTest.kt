package br.com.felipe.fairapi.application.controllers

import br.com.felipe.fairapi.application.controllers.v1.UpdateFairController
import br.com.felipe.fairapi.application.controllers.v1.request.UpdateFairRequest
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.services.UpdateFairService
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
@WebMvcTest(controllers = [UpdateFairController::class])
@AutoConfigureMockMvc
internal class UpdateFairControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var updateFairService: UpdateFairService

    @Test
    fun `should be update a fair`() {
        val updateFairRequest = mockUpdateFairRequest()

        val json = ObjectMapper().writeValueAsString(updateFairRequest)

        BDDMockito.given(updateFairService.execute(any(), any())).willReturn(mockFairResponse())

        val request = MockMvcRequestBuilders.put("/v1/fairs/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("name").value(updateFairRequest.name))
    }

    private fun mockUpdateFairRequest(): UpdateFairRequest {
        return UpdateFairRequest(
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
            id = 1,
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