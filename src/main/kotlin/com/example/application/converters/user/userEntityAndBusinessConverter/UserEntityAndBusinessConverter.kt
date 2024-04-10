package com.example.application.converters.user.userEntityAndBusinessConverter

import com.example.application.dto.user.UserBusinessDto
import com.example.application.entity.user.UserEntity

interface UserEntityAndBusinessConverter {
    fun convert(dto: UserEntity): UserBusinessDto

    fun convert(dto: UserBusinessDto): UserEntity
}
