package com.example.application.dao.user

import com.example.application.converters.user.userEntityAndBusinessConverter.UserEntityAndBusinessConverter
import com.example.application.dto.user.UserBusinessDto
import com.example.application.entity.user.UserEntity
import com.example.application.exception.DataBaseException
import com.example.application.exception.UnavailableTechnicalException
import com.example.application.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
@Primary
class UserDaoImpl : UserDao {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userConverter: UserEntityAndBusinessConverter

    override fun getUserByLogin(userLogin: String): UserBusinessDto {
        val userEntity: UserEntity
        try {
            userEntity = userRepository.getUserByLogin(userLogin)
        } catch (ex: EmptyResultDataAccessException) {
            throw DataBaseException("Cant find user by login=$userLogin")
        } catch (ex: DataAccessException) {
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        return userConverter.convert(userEntity)
    }

    override fun getUserById(userId: Long): UserBusinessDto {
        val userEntity: UserEntity
        try {
            userEntity = userRepository.getUserById(userId)
        } catch (ex: EmptyResultDataAccessException) {
            throw DataBaseException("Cant find user by id=$userId")
        } catch (ex: DataAccessException) {
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }
        return userConverter.convert(userEntity)
    }

    override fun saveNewUser(user: UserBusinessDto) {
        val userEntity: UserEntity

        try {
            userEntity = userConverter.convert(user)
        } catch (ex: Exception) {
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        userRepository.save(userEntity)
    }

    override fun updateInfoAboutUser(user: UserBusinessDto) {
        val userEntity: UserEntity
        try {
            userEntity = userRepository.getUserById(user.getId())
        } catch (ex: EmptyResultDataAccessException) {
            throw DataBaseException("Cant find user by id=${user.getId()}")
        } catch (ex: DataAccessException) {
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        userEntity.password = user.getPassword()
        userEntity.name = user.getName()
        userEntity.surname = user.getSurname()
        userEntity.lastname = user.getLastName()
        userEntity.address = user.getAddress()
        userEntity.email = user.getEmail()
        userEntity.tariffPlan = user.getTariffPlan().code

        userRepository.save(userEntity)
    }
}
