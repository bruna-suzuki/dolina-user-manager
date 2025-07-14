package com.dolina_user_manager.controllers

import com.dolina_user_manager.dtos.UserDto
import com.dolina_user_manager.models.UserModel
import com.dolina_user_manager.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody payload: UserDto): ResponseEntity<UserModel> {
        val newUser = userService.createUser(payload)
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<String> {
        userService.deleteUser(id)
        return ResponseEntity.status(HttpStatus.OK).body("User has been deleted.")
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserModel>> {
        val users = userService.getAllUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }
}