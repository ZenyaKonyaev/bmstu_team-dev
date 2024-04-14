package com.example.application.converters.user.userEntityAndBusinessConverter

import com.example.application.dto.user.UserBusinessDto
import com.example.application.entity.user.UserEntity

/**
 * Интерфейс преобразования бд и бизнес сущности пользователя
 */
interface UserEntityAndBusinessConverter {
    /**
     * преобразовать бд сущность в бизнес
     * @param dto бд сущность
     * @return бизнес сущность
     */
    fun convert(dto: UserEntity): UserBusinessDto

    /**
     * преобразовать бизнес сущность в бд
     * @param dto бизнес сущность
     * @return бд сущность
     */
    fun convert(dto: UserBusinessDto): UserEntity
}
