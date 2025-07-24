package com.dolina_user_manager.controllers

import com.dolina_user_manager.dtos.requests.RequestValidateUserDto
import com.dolina_user_manager.dtos.responses.ResponseValidateUserDto
import com.dolina_user_manager.services.PurchaseService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-validator")
class PurchaseController(private val purchaseService: PurchaseService) {

    @PostMapping("/validation")
    fun validateUser(@RequestBody payload: RequestValidateUserDto): ResponseValidateUserDto {
        return purchaseService.validateUser(payload)
    }
}