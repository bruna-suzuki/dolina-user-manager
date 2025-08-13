package com.dolina_user_manager.services

import com.dolina_user_manager.dtos.requests.RequestUserValidatorDto
import com.dolina_user_manager.dtos.responses.ResponseUserValidatorDto
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val userService: UserService) {

    fun validatePurchase(payload: RequestUserValidatorDto): ResponseUserValidatorDto {
        val user = userService.findByGlobalId(payload.globalUserId) ?: throw Exception("User doesn't exist.")
        val response = ResponseUserValidatorDto(
            userName = user.fullName,
            address = user.address,
            email = user.email
        )
        val validateBalance = userService.validateBalance(payload.amount, payload.globalUserId)
        if (validateBalance){
            return response
        } else {
            throw Exception("Insufficient balance")
        }
    }
}