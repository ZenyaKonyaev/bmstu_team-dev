package com.example.application.repository

import com.example.application.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Transactional
@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.login = ?1")
    fun getUserByLogin(userLogin: String): UserEntity

    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
    fun getUserById(userId: Long): UserEntity
}
