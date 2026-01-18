package com.dolina_user_manager.service.unitTest

import com.dolina_user_manager.models.UserModel
import com.dolina_user_manager.services.PurchaseService
import com.dolina_user_manager.services.UserService
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import kotlin.test.Test


@ExtendWith(MockKExtension::class)
class PurchaseServiceTest {


    @MockK
    lateinit var userService: UserService

    @InjectMocks
    lateinit var purchaseService: PurchaseService

    @Test
    fun `should return valid balance`() {

        val user = UserModel(
            fullName = "João Silva",
            address = "Rua Girasol",
            phoneNumber = "11945789234",
            email = "joaosilva@email.com"
        )



    }


}