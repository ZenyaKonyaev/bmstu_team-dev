package com.example.application.converters.user.userEntityAndBusinessConverter

import com.example.application.dto.user.UserBusinessDto
import com.example.application.dto.user.UserBusinessDtoImpl
import com.example.application.entity.user.UserEntity
import com.example.application.enumerations.Roles
import com.example.application.enumerations.TariffPlan
import org.springframework.stereotype.Component
import java.sql.Date

/**
 * Реализация UserEntityAndBusinessConverter для UserBusinessDtoImpl и UserEntity
 */
@Component
class UserEntityAndBusinessConverterImpl : UserEntityAndBusinessConverter {
    override fun convert(dto: UserEntity): UserBusinessDto {
        return UserBusinessDtoImpl(
            id = dto.id,
            login = dto.login,
            password = dto.password,
            name = dto.name,
            surname = dto.surname,
            lastname = dto.lastname,
            address = dto.address,
            regDate = java.util.Date(dto.regdate.time),
            email = dto.email ?: "",
            tariffPlan = TariffPlan.findTariffPlanByCode(dto.tariffPlan),
            role = Roles.findRoleByString(dto.role),
        )
    }

    override fun convert(dto: UserBusinessDto): UserEntity {
        return UserEntity(
            id = dto.getId(),
            login = dto.getLogin(),
            password = dto.getPassword(),
            name = dto.getName(),
            surname = dto.getSurname(),
            lastname = dto.getLastName(),
            address = dto.getAddress(),
            regdate = Date(dto.getRegDate().time),
            email = dto.getEmail(),
            tariffPlan = dto.getTariffPlan().code,
            role = dto.getRoles().first().role,
        )
    }
}
