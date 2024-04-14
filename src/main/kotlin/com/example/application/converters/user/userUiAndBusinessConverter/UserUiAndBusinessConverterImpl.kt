package com.example.application.converters.user.userUiAndBusinessConverter

import com.example.application.dto.user.UserBusinessDto
import com.example.application.dto.user.UserBusinessDtoImpl
import com.example.application.dto.user.UserUIDto
import com.example.application.enumerations.Roles
import org.springframework.stereotype.Component

/**
 * Реализация UserUiAndBusinessConverter для типов UserUIDto и UserBusinessDtoImpl
 */
@Component
class UserUiAndBusinessConverterImpl : UserUiAndBusinessConverter {
    override fun convert(businessDto: UserBusinessDto) =
        UserUIDto(
            businessDto.getId(),
            businessDto.getName(),
            businessDto.getSurname(),
            businessDto.getLastName(),
            businessDto.getAddress(),
            businessDto.getRegDate(),
            businessDto.getEmail(),
        )

    override fun convert(
        uiDto: UserUIDto,
        login: String,
        password: String,
        role: Roles,
    ) = UserBusinessDtoImpl(
        login = login,
        password = password,
        name = uiDto.name,
        surname = uiDto.surname,
        lastname = uiDto.lastname,
        address = uiDto.address,
        email = uiDto.email,
        role = role,
    )
}
