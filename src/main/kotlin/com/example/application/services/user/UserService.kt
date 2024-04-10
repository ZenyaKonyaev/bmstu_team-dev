package com.example.application.services.user

import com.example.application.dto.user.UserBusinessDto

interface UserService {
    fun getUser(login: String): UserBusinessDto?

    fun getUser(id: Long): UserBusinessDto?

    fun updateUser(userBusinessDto: UserBusinessDto)

    fun registerUser(userBusinessDto: UserBusinessDto)
}
