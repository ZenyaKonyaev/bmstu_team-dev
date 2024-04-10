package com.example.application.controller

import com.example.application.config.datasource.DataSourceContextHolder
import com.example.application.config.datasource.DataSourceEnum
import com.example.application.converters.cake.cakeUiAndBusinessConverter.CakeUiAndBusinessConverter
import com.example.application.converters.order.orderUiAndBusinessConverter.OrderUiAndBusinessConverter
import com.example.application.converters.product.productUiAndBusinessConverter.ProductUiAndBusinessConverter
import com.example.application.converters.user.userUiAndBusinessConverter.UserUiAndBusinessConverter
import com.example.application.dto.order.OrderUIDto
import com.example.application.dto.rq.RequestOrderDto
import com.example.application.exception.UnavailableTechnicalException
import com.example.application.services.order.OrderService
import com.example.application.services.product.ProductService
import com.example.application.services.user.UserService
import com.example.application.services.validation.order.OrderValidationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.util.*

@RestController
@RequestMapping("/order")
@SessionAttributes("orderData")
class OrderController {
    @Autowired
    private lateinit var orderValidationService: OrderValidationService

    @Autowired
    private lateinit var orderConverter: OrderUiAndBusinessConverter

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userConverter: UserUiAndBusinessConverter

    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var productConverter: ProductUiAndBusinessConverter

    @Autowired
    private lateinit var cakeConverter: CakeUiAndBusinessConverter

    @Autowired
    private lateinit var dataSourceContextHolder: DataSourceContextHolder

    private val logger = LogManager.getLogger(OrderController::class.java)

    private val errorMsgRs = "Ошибка при валидации заказа!"

    @PostMapping
    fun getOrderPage(
        @RequestParam("dataCart") dtoString: String,
        @AuthenticationPrincipal user: User
    ): ModelAndView {
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_AUTH)

        logger.info("Entering to order page by user with login ${user.username} and productCart=$dtoString")

        val dto: RequestOrderDto
        try {
            dto = ObjectMapper().readValue(dtoString, RequestOrderDto::class.java)
        } catch (ex: Exception) {
            logger.error("Cant parse dataCart String to RequestOrderDto class")
            throw UnavailableTechnicalException("Cant parse dataCart String to RequestOrderDto class")
        }

        logger.debug("Converted dataCart: $dto")

        val orderUiDto = OrderUIDto(
            id = UUID.randomUUID().toString(),
            products = dto.productData.map {
                productConverter.convert(productService.getProductById(it.intId.toLong()))
            },
            cakes = dto.cakesData.map {
                cakeConverter.convert(productService.getCakeByPartIds(
                    it.baseId.toLong(), it.fillingId.toLong(), it.creamId.toLong()))
            }
        )
        val userDto = userConverter.convert(userService.getUser(user.username)!!)

        dataSourceContextHolder.clearContext()

        return ModelAndView().apply {
            addObject("orderData", orderUiDto)
            addObject("user", userDto)
            viewName = "order"
        }
    }

    @PostMapping("/create")
    fun createOrder(
        @ModelAttribute("orderData") orderUIDto: OrderUIDto,
        @AuthenticationPrincipal user: User
    ): ModelAndView {
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_AUTH)

        logger.info("Creating order by user with username=${user.username} and Order=${orderUIDto}")
        val userDto = userService.getUser(user.username)!!
        logger.debug("UserDto after converting AuthenticationPrincipal: id=${userDto.getId()}, login=${userDto.getLogin()}")
        val orderDto = orderConverter.convert(orderUIDto, userDto)
        logger.debug("OrderDto after converting from orderUIDto: id=${orderDto.getOrderId()}, userId=${userDto.getId()}, userLogin=${userDto.getLogin()}")

        if (!orderValidationService.isCorrectOrder(orderDto)) ModelAndView().apply {
            addObject("errorText", errorMsgRs)
            viewName = "order"
        }

        orderService.createOrder(orderDto)

        dataSourceContextHolder.clearContext()

        return ModelAndView().apply {
            viewName = "redirect:/index"
        }

    }
}