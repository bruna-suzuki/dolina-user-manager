package com.dolina_user_manager.controllers

import com.dolina_user_manager.dtos.UserDto
import com.dolina_user_manager.models.UserModel
import com.dolina_user_manager.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody payload: UserDto): ResponseEntity<UserModel> {
        val newUser = userService.createUser(payload)
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser)
    }
}