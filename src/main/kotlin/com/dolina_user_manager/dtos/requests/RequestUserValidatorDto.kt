package com.dolina_user_manager.dtos.requests

import java.math.BigDecimal
import java.util.UUID

data class RequestUserValidatorDto(
    val globalUserId: UUID,
    val amount: BigDecimal
)