package com.dolina_user_manager.dtos.responses

data class ResponseValidateUserDto(
    val userName: String,
    val userAddress: String,
    val userEmail: String
)
