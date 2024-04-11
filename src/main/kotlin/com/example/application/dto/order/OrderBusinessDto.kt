package com.example.application.dto.order

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.product.ProductBusinessDto
import com.example.application.dto.user.UserBusinessDto
import com.example.application.enumerations.OrderStatusCode
import java.util.*

interface OrderBusinessDto {
    fun getOrderId(): String

    fun getUser(): UserBusinessDto

    fun getDateCreate(): Date

    fun getDateExpiry(): Date

    fun getAddressToSend(): String

    fun getDescription(): String

    fun getStatusCode(): OrderStatusCode

    fun getProducts(): List<ProductBusinessDto>

    fun getCakes(): List<CakeBusinessDto>
}
