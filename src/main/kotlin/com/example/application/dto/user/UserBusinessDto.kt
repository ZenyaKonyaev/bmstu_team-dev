package com.example.application.dto.user

import com.example.application.enumerations.Roles
import com.example.application.enumerations.TariffPlan
import java.util.*

/**
 * Интерфейс бизнес-сущности пользователя
 */
interface UserBusinessDto {
    /**
     * Получить идентификатор
     */
    fun getId(): Long

    /**
     * Получить логин
     */
    fun getLogin(): String

    /**
     * Получить пароль
     */

    fun getPassword(): String

    /**
     * Получить имя
     */

    fun getName(): String

    /**
     * Получить фамилию
     */

    fun getSurname(): String

    /**
     * Получить отчество
     */

    fun getLastName(): String

    /**
     * Получить адресс
     */

    fun getAddress(): String

    /**
     * Получить дату регистрации
     */

    fun getRegDate(): Date

    /**
     * Получить емейл
     */

    fun getEmail(): String

    /**
     * Получить тарифный план
     */

    fun getTariffPlan(): TariffPlan

    /**
     * Получить роли
     */

    fun getRoles(): List<Roles>

    /**
     * Установить идентификатор
     */

    fun setId(id: Long): UserBusinessDto
    /**
     * Установить логин
     */
    fun setLogin(login: String): UserBusinessDto
    /**
     * Установить пароль
     */

    fun setPassword(password: String): UserBusinessDto
    /**
     * Установить имя
     */

    fun setName(name: String): UserBusinessDto
    /**
     * Установить фамилию
     */

    fun setSurname(surname: String): UserBusinessDto
    /**
     * Установить отчество
     */

    fun setLastName(lastName: String): UserBusinessDto
    /**
     * Установить адресс
     */

    fun setAddress(address: String): UserBusinessDto
    /**
     * Установить емейл
     */

    fun setEmail(email: String): UserBusinessDto
}
