package com.dolina_user_manager.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "tb_user")
data class UserModel(

    @Id
    val id: UUID = UUID.randomUUID(),

    val globalUserId: UUID = UUID.randomUUID(),

    val fullName: String,

    val address: String,

    val phoneNumber: String,

    @Column(unique = true)
    val email: String,

    val balance: BigDecimal = BigDecimal.valueOf(1000),
)
