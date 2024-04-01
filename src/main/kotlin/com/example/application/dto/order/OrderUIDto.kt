package com.example.application.dto.order

import com.example.application.dto.cake.CakeUIDto
import com.example.application.enumerations.OrderStatusCode
import com.example.application.dto.product.ProductUIDto
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class OrderUIDto(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("dateCreate")
    val dateCreate: Date = Date(),
    @JsonProperty("dateExpiry")
    val dateExpiry: Date = Date(),
    @JsonProperty("addressToSend")
    val addressToSend: String = "",
    @JsonProperty("description")
    val description: String = "",
    @JsonProperty("statusCode")
    val statusCode: OrderStatusCode = OrderStatusCode.UNKNOWN,
    @JsonProperty("products")
    val products: List<ProductUIDto> = listOf(),
    @JsonProperty("cakes")
    val cakes: List<CakeUIDto> = listOf()
)
