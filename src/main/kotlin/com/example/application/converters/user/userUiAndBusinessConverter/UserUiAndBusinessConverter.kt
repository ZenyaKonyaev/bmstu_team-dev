package com.example.application.converters.user.userUiAndBusinessConverter

import com.example.application.dto.user.UserBusinessDto
import com.example.application.dto.user.UserUIDto
import com.example.application.enumerations.Roles

interface UserUiAndBusinessConverter {
    fun convert(businessDto: UserBusinessDto): UserUIDto

    fun convert(uiDto: UserUIDto, login: String, password: String, role: Roles): UserBusinessDto
}