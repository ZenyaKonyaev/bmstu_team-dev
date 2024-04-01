package com.example.application.converters.order.orderUiAndBusinessConverter

import com.example.application.dto.order.OrderBusinessDto
import com.example.application.dto.order.OrderUIDto
import com.example.application.dto.user.UserBusinessDto

interface OrderUiAndBusinessConverter {
    fun convert(uiDto: OrderUIDto, userDto: UserBusinessDto): OrderBusinessDto
    fun convert(businessDto: OrderBusinessDto): OrderUIDto
}