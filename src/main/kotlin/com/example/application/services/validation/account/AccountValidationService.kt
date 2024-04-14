package com.example.application.services.validation.account

import com.example.application.dto.user.UserBusinessDto
import com.example.application.exception.DataBaseException


/**
 * Бизнес сервис по валидации бизнес-сущности пользователя
 */
interface AccountValidationService {

    /**
     * Валидировать пользователя
     * @param target валидируемая бизнес-сущность пользователя
     * @return список ошибок, найденных при валидации
     */
    fun validate(target: UserBusinessDto): List<DataBaseException>
}
