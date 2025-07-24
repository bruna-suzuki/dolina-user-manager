package com.dolina_user_manager.producers

import com.dolina_user_manager.dtos.MessageDeletionDto
import com.dolina_user_manager.dtos.MessageWelcomeDto
import com.dolina_user_manager.models.UserModel
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class UserProducer(private val rabbitTemplate: RabbitTemplate) {

    @Value("\${broker.queue.email.name}")
    lateinit var queueName: String //default.email

    @Value("\${broker.queue.delete.email.name}")
    lateinit var deleteUserQueue: String

    fun publishMessage(userModel: UserModel) {
        val message = MessageWelcomeDto(
            fullName = userModel.fullName,
            email = userModel.email
        )
        rabbitTemplate.convertAndSend("", queueName, message)
    }

    fun publishDeleteMessage(userModel: UserModel) {
        val message = MessageDeletionDto(
            userName = userModel.fullName,
            userEmail = userModel.email
        )
        rabbitTemplate.convertAndSend("", deleteUserQueue, message)
    }
}