package com.example.application.converters.user.userUiAndBusinessConverter

import com.example.application.dto.user.UserBusinessDto
import com.example.application.dto.user.UserUIDto
import com.example.application.enumerations.Roles

/**
 * Интерфейс преобразования ui и бизнес сущности пользователя
 */
interface UserUiAndBusinessConverter {
    /**
     * преобразовать бизнес сущность в ui
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: UserBusinessDto): UserUIDto

    /**
     * преобразовать бизнес ui в бизнес
     * @param uiDto ui сущность
     * @param login логин пользователя
     * @param password пароль пользователя
     * @param role роль пользователя
     * @return бизнес сущность
     */
    fun convert(
        uiDto: UserUIDto,
        login: String,
        password: String,
        role: Roles,
    ): UserBusinessDto
}
