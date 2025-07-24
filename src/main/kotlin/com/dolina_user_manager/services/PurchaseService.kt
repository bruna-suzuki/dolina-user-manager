package com.dolina_user_manager.services

import com.dolina_user_manager.dtos.requests.RequestValidateUserDto
import com.dolina_user_manager.dtos.responses.ResponseValidateUserDto
import org.springframework.stereotype.Service

@Service
class PurchaseService(private val userService: UserService) {

    fun validateUser(payload: RequestValidateUserDto): ResponseValidateUserDto {
        val user = userService.findByGlobalId(payload.globalUserId) ?: throw Exception("User doesn't exist.")
        val response = ResponseValidateUserDto(
            userName = user.fullName,
            userAddress = user.address,
            userEmail = user.email
        )
        val validateBalance = userService.validateBalance(payload.itemPrice, payload.globalUserId)
        if (validateBalance){
            return response
        } else {
            throw Exception("Insufficient balance")
        }
    }
}