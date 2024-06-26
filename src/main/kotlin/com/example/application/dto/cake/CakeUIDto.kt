package com.example.application.dto.cake

import com.example.application.dto.cakePart.CakePartUIDto
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * UI сущность торта
 * @param idCake идентификатор торта
 * @param base основа торта
 * @param filling начинка торта
 * @param cream крем торта
 * @param totalCost общая стоимость торта
 */
data class CakeUIDto(
    @JsonProperty
    val idCake: Long = 0,
    @JsonProperty
    val base: CakePartUIDto,
    @JsonProperty
    val filling: CakePartUIDto,
    @JsonProperty
    val cream: CakePartUIDto,
    @JsonProperty
    val totalCost: Double = 0.0,
)
