package br.com.felipe.fairapi.infra.configurations

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMqConfig {
    @Value("\${spring.rabbitmq.exchange}")
    lateinit var exchangeName: String;

    @Value("\${spring.rabbitmq.queue}")
    lateinit var queueName: String;

    @Value("\${spring.rabbitmq.routing-key}")
    lateinit var routingKey: String;

    @Bean
    fun declareExchange() : Exchange {
        return ExchangeBuilder.directExchange(exchangeName)
            .durable(true)
            .build()
    }

    @Bean
    fun declareQueue(): Queue {
        return QueueBuilder.durable(queueName)
            .build()
    }

    @Bean
    fun declareBinding(exchange: Exchange?, queue: Queue?): Binding? {
        return BindingBuilder.bind(queue)
            .to(exchange)
            .with(routingKey)
            .noargs()
    }
}