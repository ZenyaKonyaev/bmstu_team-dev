package com.example.application.dao.user

import com.example.application.dto.user.UserBusinessDto

/**
 * Интерфейс компонента доступа к данным для пользователя
 */
interface UserDao {
    /**
     * Получить пользователя по его логину
     * @param userLogin логин запрашиваемого пользователя
     * @return бизнес-сущность запрашиваемого пользователя
     */
    fun getUserByLogin(userLogin: String): UserBusinessDto

    /**
     * Получить пользователя по его идентификатору
     * @param userId идентификатор запрашиваемого пользователя
     * @return бизнес-сущность запрашиваемого пользователя
     */
    fun getUserById(userId: Long): UserBusinessDto

    /**
     * Сохранить нового пользователя в бд
     * @param user новый пользователь в базе данных
     */
    fun saveNewUser(user: UserBusinessDto)

    /**
     * Обновить информацию о существующем пользователе в базе данных
     * @param user пользователь, информация о котором обновляется
     */
    fun updateInfoAboutUser(user: UserBusinessDto)
}
