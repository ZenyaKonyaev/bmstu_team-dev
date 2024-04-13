package com.example.application.validation

import com.example.application.dto.user.UserBusinessDtoImpl
import com.example.application.services.user.UserService
import com.example.application.services.validation.account.AccountValidationServiceImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unit")
class AccountValidationServiceImplTest {
    @InjectMockKs
    private lateinit var accountValidationService: AccountValidationServiceImpl

    @MockK
    private lateinit var userService: UserService

    @BeforeEach
    fun initMock() {
        MockKAnnotations.init(this)
    }

    @Test
    fun positiveValidationTest() {
        every { userService.getUser("superlogin123") } returns null
        val dto = UserBusinessDtoImpl().apply {
            setLogin("superlogin123")
            setPassword("superPassword123")
        }

        val results = accountValidationService.validate(dto)
        assertTrue{  results.isEmpty() }
    }


    @Test
    fun negativeValidationTest() {
        val dataProvider = arrayOf(
            arrayOf("", "1244353"),
            arrayOf("213", "1224512"),
            arrayOf("1".repeat(40), "dsfdfwesaafg")
        )

        dataProvider.forEach { it ->
            val login = it[0]
            val password = it[1]
            every { userService.getUser(login) } returns null
            val dto = UserBusinessDtoImpl().apply {
                setLogin(login)
                setPassword(password)
            }

            val results = accountValidationService.validate(dto)

            assertFalse{  results.isEmpty() }
        }
    }
}