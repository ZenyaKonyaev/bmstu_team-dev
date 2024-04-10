package com.example.application.dto.rs

import com.example.application.dto.order.OrderUIDto
import com.fasterxml.jackson.annotation.JsonProperty

data class HistoryPageRs(
    @JsonProperty
    val orders: List<OrderUIDto>?
)