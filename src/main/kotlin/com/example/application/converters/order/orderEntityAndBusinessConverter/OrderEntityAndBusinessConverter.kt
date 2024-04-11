package com.example.application.converters.order.orderEntityAndBusinessConverter

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.entity.order.OrderEntity

interface OrderEntityAndBusinessConverter {
    fun convert(dto: OrderBusinessDto): OrderEntity

    fun convert(dto: OrderEntity): OrderBusinessDto
}
