package com.example.application.dto.order

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.product.ProductBusinessDto
import com.example.application.dto.user.UserBusinessDto
import com.example.application.enumerations.OrderStatusCode
import java.util.*

/**
 * Интерфейс бизнес-сущности заказа
 */
interface OrderBusinessDto {
    /**
     * Получить идентификатор
     */
    fun getOrderId(): String

    /**
     * Получить владельца
     */
    fun getUser(): UserBusinessDto
    /**
     * Получить дату создания
     */

    fun getDateCreate(): Date
    /**
     * Получить дату доставки
     */

    fun getDateExpiry(): Date
    /**
     * Получить аддрес доставки
     */

    fun getAddressToSend(): String
    /**
     * Получить описание
     */

    fun getDescription(): String
    /**
     * Получить статус заказа
     */

    fun getStatusCode(): OrderStatusCode
    /**
     * Получить продукты в заказе
     */

    fun getProducts(): List<ProductBusinessDto>
    /**
     * Получить торты в заказе
     */

    fun getCakes(): List<CakeBusinessDto>
}
