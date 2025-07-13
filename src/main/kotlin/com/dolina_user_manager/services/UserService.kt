package com.dolina_user_manager.services

import com.dolina_user_manager.dtos.UserDto
import com.dolina_user_manager.mappers.toUserModel
import com.dolina_user_manager.models.UserModel
import com.dolina_user_manager.producers.UserProducer
import com.dolina_user_manager.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userProducer: UserProducer) {

    fun createUser(payload: UserDto): UserModel {
        val newUser = payload.toUserModel()
        userProducer.publishMessage(newUser)
        userRepository.save(newUser)
        return newUser
    }

    fun deleteUser(userId: UUID) {
        val deleteUser = userRepository.findById(userId)
            .orElseThrow { Exception("User not found.") }

        userRepository.delete(deleteUser)

        userProducer.publishDeleteMessage(deleteUser)
    }
}