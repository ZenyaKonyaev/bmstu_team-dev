package com.example.application.dto.product

import com.example.application.dto.bonus.BonusUIDto
import com.example.application.dto.description.DescriptionUIDto
import com.fasterxml.jackson.annotation.JsonProperty

class ProductUIDto(
    @JsonProperty
    val id: Long = 0L,
    @JsonProperty
    val name: String,
    @JsonProperty
    val cost: Double,
    @JsonProperty
    val urlImg: String,
    @JsonProperty
    val description: DescriptionUIDto = DescriptionUIDto("", "", "", 0.0, 0.0, 0.0, 0),
    @JsonProperty
    val bonuses: List<BonusUIDto>? = null
)