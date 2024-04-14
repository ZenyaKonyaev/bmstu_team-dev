package com.example.application.controller

import com.example.application.config.datasource.DataSourceContextHolder
import com.example.application.config.datasource.DataSourceEnum
import com.example.application.converters.order.orderUiAndBusinessConverter.OrderUiAndBusinessConverter
import com.example.application.converters.user.userUiAndBusinessConverter.UserUiAndBusinessConverter
import com.example.application.dto.rs.InfoAccountPageRs
import com.example.application.dto.user.UserUIDto
import com.example.application.services.order.OrderService
import com.example.application.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


/**
 * Контроллер аккаунта пользователя. Все запросы делаются с префиксом /account
 */
@RestController
@RequestMapping("/account")
class UserAccountController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var userConverter: UserUiAndBusinessConverter

    @Autowired
    private lateinit var orderConverter: OrderUiAndBusinessConverter

    @Autowired
    private lateinit var dataSourceContextHolder: DataSourceContextHolder

    /**
     * Получить страницу личного кабинета пользователя
     * @param user пользователь для которого запрашивается страница личного кабинета, полученный при авторизации через WebSpringSecurity
     * @return представление страницы личного кабинета пользователя, а также объекты responseData (InfoAccountPageRs) и updatedUser (UserUIDto)
     */
    @GetMapping
    fun getInfoAccountData(
        @AuthenticationPrincipal user: User,
    ): ModelAndView {
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_AUTH)

        val userDto = userService.getUser(user.username)!!
        val orders = orderService.getUserOrders(userDto)

        val response =
            InfoAccountPageRs(
                userConverter.convert(
                    userService.getUser(user.username)!!,
                ),
                orders.map { orderConverter.convert(it) },
            )

        dataSourceContextHolder.clearContext()

        return ModelAndView().apply {
            viewName = "account"
            addObject("responseData", response)
            addObject("updatedUser", UserUIDto())
        }
    }

    @PostMapping("/updateInfo")
    fun changeInfoAccount(
        @AuthenticationPrincipal user: User,
        @ModelAttribute("updatedUser") updatedUserUIDto: UserUIDto,
    ): ModelAndView {
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_AUTH)

        val userDto = userService.getUser(user.username)!!
        userDto.setName(updatedUserUIDto.name)
            .setSurname(updatedUserUIDto.surname)
            .setLastName(updatedUserUIDto.lastname)
            .setEmail(updatedUserUIDto.email)
            .setAddress(updatedUserUIDto.address)

        userService.updateUser(userDto)

        dataSourceContextHolder.clearContext()

        return ModelAndView().apply {
            viewName = "redirect:/index"
        }
    }
}
