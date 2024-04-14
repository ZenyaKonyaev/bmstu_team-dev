package com.example.application.services.user

import com.example.application.dao.user.UserDao
import com.example.application.dto.user.UserBusinessDto
import com.example.application.exception.BaseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Реализация UserService
 */
@Service
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    override fun getUser(login: String) =
        try {
            userDao.getUserByLogin(login)
        } catch (ex: BaseException) {
            null
        }

    override fun getUser(id: Long) =
        try {
            userDao.getUserById(id)
        } catch (ex: BaseException) {
            null
        }

    override fun updateUser(userBusinessDto: UserBusinessDto) {
        try {
            userDao.updateInfoAboutUser(userBusinessDto)
        } catch (ex: BaseException) {
        }
    }

    override fun registerUser(userBusinessDto: UserBusinessDto) {
        userBusinessDto.setPassword(passwordEncoder.encode(userBusinessDto.getPassword()))
        try {
            userDao.saveNewUser(userBusinessDto)
        } catch (ex: BaseException) {
        }
    }
}
