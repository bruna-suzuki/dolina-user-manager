package com.dolina_user_manager.services

import com.dolina_user_manager.dtos.requests.RequestUserValidatorDto
import com.dolina_user_manager.dtos.responses.ResponseUserValidatorDto
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val userService: UserService) {

    fun validatePurchase(payload: RequestUserValidatorDto): ResponseUserValidatorDto {
        val user = userService.findByGlobalId(payload.globalUserId) ?: throw Exception("User not found.")
        val balance = userService.validateBalance(payload.amount, payload.globalUserId)
        if (balance) {
            val response = ResponseUserValidatorDto(
                userName = user.fullName,
                address = user.address,
                email = user.email)
            return response
        } else {
            throw Exception("Insufficient balance.")
        }
    }
}