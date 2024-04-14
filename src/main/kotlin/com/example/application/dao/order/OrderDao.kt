package com.example.application.dao.order

import com.example.application.dto.order.OrderBusinessDto

/**
 * Интерфейс компонента доступа к данным для заказа
 */
interface OrderDao {
    /**
     * Получить заказ по его идентификатору
     * @param orderId идентификатор заказа
     * @return бизнес сущность заказа
     */
    fun getOrderById(orderId: String): OrderBusinessDto

    /**
     * Получить все заказы определенного пользователя
     * @param userId идентификатор пользователя, для которого запрашиваются заказы
     * @return список заказов данного пользователя (бизнес-сущности)
     */
    fun getUserOrders(userId: Long): List<OrderBusinessDto>

    /**
     * Создать новый заказ
     * @param order бизнес-сущность создаваемого заказа
     * @return ничего
     */
    fun createNewOrder(order: OrderBusinessDto)
}
