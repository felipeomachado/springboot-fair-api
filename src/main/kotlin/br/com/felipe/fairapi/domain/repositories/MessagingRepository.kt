package br.com.felipe.fairapi.domain.repositories

interface MessagingRepository {
    fun sendMessage(message: String)
}