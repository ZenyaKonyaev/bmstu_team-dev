package com.example.application.services.validation.account

import com.example.application.dto.user.UserBusinessDto
import com.example.application.exception.DataBaseException

interface AccountValidationService {
    fun validate(target: UserBusinessDto): List<DataBaseException>
}
