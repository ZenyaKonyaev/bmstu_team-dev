package com.example.application.repository

import com.example.application.entity.order.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

/**
 * JPA репозиторий заказа
 */
@Repository
@Transactional
interface OrderRepository : JpaRepository<OrderEntity, Long> {
    /**
     * Получить заказы по id его владельца
     * @param userId id владельца заказа
     * @return список бд сущностей заказов владельца
     */
    @Query("SELECT o FROM OrderEntity o WHERE o.user.id =  ?1")
    fun getUserOrders(userId: Long): List<OrderEntity>

    /**
     * Получить заказ по его id
     * @param orderId id заказа
     * @return бд сущность заказа
     */
    @Query("SELECT o FROM OrderEntity o WHERE o.id =  ?1")
    fun getOrderById(orderId: String): OrderEntity
}
