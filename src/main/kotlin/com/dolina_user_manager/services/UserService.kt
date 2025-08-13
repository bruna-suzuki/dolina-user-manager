package com.dolina_user_manager.services

import com.dolina_user_manager.dtos.AddMoneyDto
import com.dolina_user_manager.dtos.UserDto
import com.dolina_user_manager.dtos.requests.RequestUserValidatorDto
import com.dolina_user_manager.dtos.responses.ResponseUserValidatorDto
import com.dolina_user_manager.models.UserModel
import com.dolina_user_manager.producers.UserProducer
import com.dolina_user_manager.repositories.UserRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userProducer: UserProducer) {

    fun createUser(payload: UserDto): UserModel {
        val newUser = UserModel(
            fullName = payload.fullName,
            email = payload.email,
            address = payload.address,
            phoneNumber = payload.phoneNumber
        )
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

    fun getAllUsers(): List<UserModel> {
        return userRepository.findAll()
    }

    fun validatePurchase(payload: RequestUserValidatorDto): ResponseUserValidatorDto {
        val user = findByGlobalId(payload.globalUserId) ?: throw Exception("User not found.")
        val balance = validateBalance(payload.amount, payload.globalUserId)
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

    fun findByGlobalId(globalUserId: UUID): UserModel? {
        return userRepository.findByGlobalUserId(globalUserId)
    }

    fun validateBalance(amount: BigDecimal, globalUserId: UUID): Boolean {
        val user = userRepository.findByGlobalUserId(globalUserId)
        if (user!!.balance!!.compareTo(amount) >= 0) {
            return true
        } else {
            return false
        }
    }

    fun updateBalance(globalUserId: UUID, amount: BigDecimal) {
        val user = findByGlobalId(globalUserId) ?: throw Exception("User not found.")

        user.balance = user.balance.subtract(amount)
        userRepository.save(user)
    }

    fun addMoney(payload: AddMoneyDto) {
        val user = findByGlobalId(payload.globalUserId)
            ?: throw Exception("User not found.")

        user.balance = user.balance.add(payload.amount)
        userRepository.save(user)
    }
}