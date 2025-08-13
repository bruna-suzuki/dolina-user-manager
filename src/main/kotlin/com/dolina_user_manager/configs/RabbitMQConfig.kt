package com.dolina_user_manager.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Value("\${broker.queue.email.name}")
    lateinit var emailQueue: String

    @Value("\${broker.queue.delete.email.name}")
    lateinit var deleteUserQueue: String

    @Bean
    fun queue(): Queue {
        return Queue(emailQueue, true)
    }

    @Bean
    fun deleteEmailQueue(): Queue {
        return Queue(deleteUserQueue, true)
    }

    @Bean
    fun convertMessage(): Jackson2JsonMessageConverter {
        val objectMapper = jacksonObjectMapper()
        return Jackson2JsonMessageConverter(objectMapper)
    }
}