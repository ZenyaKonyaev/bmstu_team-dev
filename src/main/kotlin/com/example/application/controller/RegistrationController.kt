package com.example.application.controller

import com.example.application.config.datasource.DataSourceContextHolder
import com.example.application.config.datasource.DataSourceEnum
import com.example.application.converters.user.userUiAndBusinessConverter.UserUiAndBusinessConverter
import com.example.application.dto.user.UserUIDto
import com.example.application.enumerations.Roles
import com.example.application.services.user.UserService
import com.example.application.services.validation.account.AccountValidationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/registration")
class RegistrationController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userConverter: UserUiAndBusinessConverter

    @Autowired
    private lateinit var userAccountValidator: AccountValidationService

    @Autowired
    private lateinit var dataSourceContextHolder: DataSourceContextHolder

    @GetMapping
    fun getRegistrationPage(model: Model): String {
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_UNKNOWN)
        model.addAttribute("newUserDto", UserUIDto())
        dataSourceContextHolder.clearContext()
       return "registration"
    }

    @PostMapping
    fun registerNewUser(
        @ModelAttribute("newUserDto") newUserUIDto: UserUIDto,
        @ModelAttribute("login") loginUser: String,
        @ModelAttribute("password") passwordUser: String,
        model: Model
    ): String {
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_UNKNOWN)
        val businessDto = userConverter.convert(newUserUIDto, loginUser, passwordUser, Roles.USER)


        val errors = userAccountValidator.validate(businessDto)

        if (errors.isNotEmpty()) {
            model.addAttribute("textError", errors.joinToString { it.metaData })
            return "registration"
        }

        userService.registerUser(businessDto)

        dataSourceContextHolder.clearContext()

        return "redirect:/login"
    }
}