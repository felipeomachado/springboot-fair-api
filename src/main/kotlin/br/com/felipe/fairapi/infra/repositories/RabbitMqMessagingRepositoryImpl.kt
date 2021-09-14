package br.com.felipe.fairapi.infra.repositories

import br.com.felipe.fairapi.domain.repositories.MessagingRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RabbitMqMessagingRepositoryImpl(private val rabbitTemplate: RabbitTemplate) : MessagingRepository {
    @Value("\${spring.rabbitmq.exchange}")
    lateinit var exchangeName: String;

    override fun sendMessage(message: String) {
        rabbitTemplate.convertAndSend(exchangeName, "rk.fairapp", message)
    }
}