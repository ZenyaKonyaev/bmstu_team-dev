package com.example.application.services.user

import com.example.application.dto.user.UserBusinessDto

/**
 * Бизнес сервис по работе с пользователем
 */
interface UserService {
    /**
     * Получить пользователя по его логину
     * @param login логин запрашиваемого пользователя
     * @return бизнес-сущность запрашиваемого пользователя, если такой нашелся
     */
    fun getUser(login: String): UserBusinessDto?

    /**
     * Получить пользователя по его идентификатору
     * @param id идентификатор запрашиваемого пользователя
     * @return бизнес-сущность запрашиваемого пользователя, если такой нашелся
     */
    fun getUser(id: Long): UserBusinessDto?

    /**
     * Обновить информацию о существующем пользователе
     * @param userBusinessDto пользователь, информация о котором обновляется
     */
    fun updateUser(userBusinessDto: UserBusinessDto)

    /**
     * Зарегистрировать нового пользователя в бд
     * @param userBusinessDto новый пользователь
     */
    fun registerUser(userBusinessDto: UserBusinessDto)
}
