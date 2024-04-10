package com.example.application.dao.order

import com.example.application.dto.order.OrderBusinessDto

interface OrderDao {
    fun getOrderById(orderId: String): OrderBusinessDto

    fun getUserOrders(userId: Long): List<OrderBusinessDto>

    fun createNewOrder(order: OrderBusinessDto)
}
