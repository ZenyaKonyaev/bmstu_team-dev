package com.example.application.dto.rs

import com.example.application.enumerations.StatusCreateOrderRq
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateOrderFormRs(
    @JsonProperty
    val msg: String,
    @JsonProperty
    val status: StatusCreateOrderRq
)
