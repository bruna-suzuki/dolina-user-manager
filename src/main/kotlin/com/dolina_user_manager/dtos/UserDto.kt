package com.dolina_user_manager.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class UserDto(
    val fullName: String,
    val address: String,
    @field:Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid phone.")
    val phoneNumber: String,
    @Email
    val email: String
)
