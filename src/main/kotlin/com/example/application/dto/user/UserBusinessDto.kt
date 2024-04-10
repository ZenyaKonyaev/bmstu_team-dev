package com.example.application.dto.user

import com.example.application.enumerations.Roles
import com.example.application.enumerations.TariffPlan
import java.util.*

interface UserBusinessDto {
    fun getId(): Long

    fun getLogin(): String

    fun getPassword(): String

    fun getName(): String

    fun getSurname(): String

    fun getLastName(): String

    fun getAddress(): String

    fun getRegDate(): Date

    fun getEmail(): String

    fun getTariffPlan(): TariffPlan

    fun getRoles(): List<Roles>

    fun setId(id: Long): UserBusinessDto

    fun setLogin(login: String): UserBusinessDto

    fun setPassword(password: String): UserBusinessDto

    fun setName(name: String): UserBusinessDto

    fun setSurname(surname: String): UserBusinessDto

    fun setLastName(lastName: String): UserBusinessDto

    fun setAddress(address: String): UserBusinessDto

    fun setEmail(email: String): UserBusinessDto
}
