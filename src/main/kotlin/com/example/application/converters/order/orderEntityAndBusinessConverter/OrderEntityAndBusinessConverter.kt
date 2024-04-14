package com.example.application.converters.order.orderEntityAndBusinessConverter

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.entity.order.OrderEntity

/**
 * Преобразование бизнес и бд сущности заказа
 */
interface OrderEntityAndBusinessConverter {
    /**
     * преобразовать бизнес в бд сущность
     * @param dto бизнес сущность
     * @return бд сущность
     */
    fun convert(dto: OrderBusinessDto): OrderEntity

    /**
     * преобразование бд в бизнес сущность
     * @param dto бд сущность
     * @return бизнес сущность
     */
    fun convert(dto: OrderEntity): OrderBusinessDto
}
