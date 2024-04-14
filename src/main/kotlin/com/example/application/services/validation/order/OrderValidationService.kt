package com.example.application.services.validation.order

import com.example.application.dto.order.OrderBusinessDto

/**
 * Бизнес сервис по валидации бизнес-сущности заказа
 */
interface OrderValidationService {
    /**
     * Валидировать заказ
     * @param order бизнес-сущность валидируемого заказа
     * @return true, если заказ корректен, иначе false
     */
    fun isCorrectOrder(order: OrderBusinessDto): Boolean
}
