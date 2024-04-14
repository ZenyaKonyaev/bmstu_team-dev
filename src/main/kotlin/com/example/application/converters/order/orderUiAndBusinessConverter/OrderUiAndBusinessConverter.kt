package com.example.application.converters.order.orderUiAndBusinessConverter

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.dto.order.OrderUIDto
import com.example.application.dto.user.UserBusinessDto

/**
 * Интерфейс преобразования бизнес и ui сущности заказа
 */
interface OrderUiAndBusinessConverter {

    /**
     * преобразовать ui сущность в бизнес
     * @param uiDto ui сущность заказа
     * @param userDto бизнес сущность пользователя для которого делается заказ
     * @return
     */
    fun convert(
        uiDto: OrderUIDto,
        userDto: UserBusinessDto,
    ): OrderBusinessDto

    /**
     * Преобразовать бизнес сущность в ui сущность
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: OrderBusinessDto): OrderUIDto
}
