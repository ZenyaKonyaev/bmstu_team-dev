package com.example.application.dao.user

import com.example.application.dto.user.UserBusinessDto

interface UserDao {
    fun getUserByLogin(userLogin: String): UserBusinessDto
    fun getUserById(userId: Long): UserBusinessDto

    fun saveNewUser(user: UserBusinessDto)
    fun updateInfoAboutUser(user: UserBusinessDto)
}