package com.example.application.dto.rs

import com.example.application.dto.order.OrderUIDto
import com.example.application.dto.user.UserUIDto
import com.fasterxml.jackson.annotation.JsonProperty

data class InfoAccountPageRs(
    @JsonProperty
    val user: UserUIDto,
    @JsonProperty
    val orders: List<OrderUIDto>,
)
