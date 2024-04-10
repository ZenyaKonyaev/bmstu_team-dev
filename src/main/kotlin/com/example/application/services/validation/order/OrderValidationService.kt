package com.example.application.services.validation.order

import com.example.application.dto.order.OrderBusinessDto

interface OrderValidationService {
    fun isCorrectOrder(order: OrderBusinessDto): Boolean
}
