package com.example.application.services.validation.account

import com.example.application.dto.user.UserBusinessDto
import com.example.application.exception.DataBaseException
import com.example.application.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Реализация AccountValidationService
 */
@Service
class AccountValidationServiceImpl : AccountValidationService {
    @Autowired
    private lateinit var userService: UserService

    private val MIN_SIZE_LOGIN = 1
    private val MAX_SIZE_LOGIN = 32
    private val MIN_SIZE_PAS = 4
    private val MAX_SIZE_PAS = 32

    override fun validate(target: UserBusinessDto): List<DataBaseException> {
        val errors = mutableListOf<DataBaseException>()
        if (target.getLogin().length < MIN_SIZE_LOGIN || target.getLogin().length > MAX_SIZE_LOGIN) {
            errors.add(DataBaseException("Поле \"логин\" должно быть размером от $MIN_SIZE_LOGIN до $MAX_SIZE_LOGIN символов"))
        }
        if (userService.getUser(target.getLogin()) != null) {
            errors.add(DataBaseException("Такой логин уже используется!"))
        }

        if (target.getPassword().length < MIN_SIZE_PAS || target.getPassword().length > MAX_SIZE_PAS) {
            errors.add(DataBaseException("Поле \"пароль\" должно быть размером от $MIN_SIZE_PAS до $MAX_SIZE_PAS символов"))
        }

        return errors
    }
}
