package com.dolina_user_manager.dtos

import java.math.BigDecimal
import java.util.UUID

data class AddMoneyDto(
    val globalUserId: UUID,
    val amount: BigDecimal
)
