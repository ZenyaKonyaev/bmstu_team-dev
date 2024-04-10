package com.example.application.services.security

import com.example.application.config.datasource.DataSourceContextHolder
import com.example.application.config.datasource.DataSourceEnum
import com.example.application.enumerations.Roles
import com.example.application.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class MyDatabaseUserDetailsService: UserDetailsService {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userContextHolder: DataSourceContextHolder

    override fun loadUserByUsername(username: String?): UserDetails {
        val userDto = userService.getUser(username ?: "") ?: throw UsernameNotFoundException(username)

        return User(
            userDto.getLogin(),
            userDto.getPassword(),
            userDto.getRoles().map { SimpleGrantedAuthority(it.role) }
        )
    }

}