package com.example.application.services.order

import com.example.application.dao.order.OrderDao
import com.example.application.dto.order.OrderBusinessDto
import com.example.application.dto.user.UserBusinessDto
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Реализация OrderService
 */
@Component
class OrderServiceImpl : OrderService {
    @Autowired
    private lateinit var orderDao: OrderDao

    private val logger = LogManager.getLogger(this::class.java)

    override fun createOrder(order: OrderBusinessDto) {
        orderDao.createNewOrder(order)
    }

    override fun getOrder(id: String) =
        try {
            orderDao.getOrderById(id)
        } catch (ex: Exception) {
            logger.error("Get order by id=$id returns exception")
            null
        }

    override fun getUserOrders(user: UserBusinessDto): List<OrderBusinessDto> {
        return orderDao.getUserOrders(user.getId())
    }
}
