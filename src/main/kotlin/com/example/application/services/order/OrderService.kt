package com.example.application.services.order

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.dto.user.UserBusinessDto

interface OrderService {
    fun createOrder(order: OrderBusinessDto)

    fun getOrder(id: String): OrderBusinessDto?

    fun getUserOrders(user: UserBusinessDto): List<OrderBusinessDto>
}
