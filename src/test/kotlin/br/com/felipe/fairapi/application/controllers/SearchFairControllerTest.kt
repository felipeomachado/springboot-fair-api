package br.com.felipe.fairapi.application.controllers

import br.com.felipe.fairapi.application.controllers.v1.SearchFairController
import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.services.SearchFairService
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [SearchFairController::class])
@AutoConfigureMockMvc
internal class SearchFairControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var searchFairService: SearchFairService

    companion object {
        private const val URL_BASE = "/v1/fairs"
    }

    @Test
    fun `should be find fairs`() {
        BDDMockito.given(searchFairService.searchByFilter(any(), any(), any())).willReturn(mockFairList())

        val request = get(URL_BASE)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("content").isArray)

    }

    @Test
    fun `should be find a fair by id`() {
        BDDMockito.given(searchFairService.searchById(any())).willReturn(mockFair())

        val request = get("$URL_BASE/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("name").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("districtName").isString)
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