package com.dolina_user_manager.mappers

import com.dolina_user_manager.dtos.UserDto
import com.dolina_user_manager.models.UserModel

fun UserDto.toUserModel(): UserModel {
    return UserModel(
        fullName = this.fullName,
        address = this.address,
        phoneNumber = this.phoneNumber,
        email = this.email
    )
}