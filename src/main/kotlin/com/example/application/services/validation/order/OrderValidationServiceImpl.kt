package com.example.application.services.validation.order

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.enumerations.OrderStatusCode
import com.example.application.services.order.OrderService
import com.example.application.services.product.ProductService
import com.example.application.services.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderValidationServiceImpl: OrderValidationService {
    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var orderService: OrderService

    override fun isCorrectOrder(order: OrderBusinessDto): Boolean {
        val orderUser = order.getUser()
        if (userService.getUser(orderUser.getId())?.equals(orderUser) != true) return false
        if (orderService.getOrder(order.getOrderId()) != null) return false
        if (order.getStatusCode() != OrderStatusCode.PREPARE) return false

        val productsCatalog = productService.getProductCatalog()
        order.getProducts().forEach { productFromDto ->
            if (!productsCatalog.any { it == productFromDto}) return false
        }

        val cakesPart = productService.let {
            it.getCakeCreamParts() + it.getCakeBaseParts() + it.getCakeFillingParts()
        }
        order.getCakes().forEach { cakeFromDto ->
            if (!cakesPart.any { it == cakeFromDto.getBase() } ||
                !cakesPart.any { it == cakeFromDto.getCream() } ||
                !cakesPart.any { it == cakeFromDto.getFilling() })
                return false
        }

        return true
    }
}