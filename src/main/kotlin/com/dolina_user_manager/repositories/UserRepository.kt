package com.dolina_user_manager.repositories

import com.dolina_user_manager.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserModel, UUID>{
}