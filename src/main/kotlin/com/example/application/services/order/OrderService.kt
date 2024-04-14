package com.example.application.services.order

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.dto.user.UserBusinessDto

/**
 * Бизнес сервис по работе с заказами
 */
interface OrderService {
    /**
     * Создать заказ
     * @param order заказ, который хотим создать
     */
    fun createOrder(order: OrderBusinessDto)

    /**
     * Получить заказ
     * @param id id заказа, который хотим получить
     * @return бизнес сущность заказа (если такой есть), иначе null
     */
    fun getOrder(id: String): OrderBusinessDto?

    /**
     * Получить заказы пользователя
     * @param user бизнес сущность пользователя, заказы которого хотим получить
     * @return список бизнес сущностей заказов пользователя
     */
    fun getUserOrders(user: UserBusinessDto): List<OrderBusinessDto>
}
