package br.com.felipe.fairapi.application.handlers

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorMessage(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val message: String? = null,
    val fieldErrors: MutableList<Map<String, String>>? = null
)