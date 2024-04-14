package com.example.application.dto.rs

import com.example.application.dto.order.OrderUIDto
import com.example.application.dto.user.UserUIDto
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * ДТО для личного кабинета пользователя
 * @param user данные о пользователе
 * @param orders заказы пользователя
 */
data class InfoAccountPageRs(
    @JsonProperty
    val user: UserUIDto,
    @JsonProperty
    val orders: List<OrderUIDto>,
)
