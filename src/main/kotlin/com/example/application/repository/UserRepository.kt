package com.example.application.repository

import com.example.application.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

/**
 * JPA репозиторий пользователя
 */
@Transactional
@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    /**
     * Получить пользователя по его логину
     * @param userLogin логин запрашиваемого пользователя
     * @return бд сущность пользователя
     */
    @Query("SELECT u FROM UserEntity u WHERE u.login = ?1")
    fun getUserByLogin(userLogin: String): UserEntity

    /**
     * Получить пользователя по его id
     * @param userId id запрашиваемого пользователя
     * @return бд сущность пользователя
     */
    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
    fun getUserById(userId: Long): UserEntity
}
