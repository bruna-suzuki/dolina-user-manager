package com.dolina_user_manager.consumers

import com.dolina_user_manager.dtos.requests.RequestUserValidatorDto
import com.dolina_user_manager.services.UserService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class PurchaseConsumer(private val userService: UserService) {

    @RabbitListener(queues = ["\${broker.queue.purchase.name}"])
    fun receiveFromBroker(@Payload data: RequestUserValidatorDto) {
        userService.updateBalance(data.globalUserId, data.amount)
    }
}