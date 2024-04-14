package com.example.application.dto.description

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * UI сущность описания
 * @param title заголовок
 * @param description описание
 * @param urlImg путь к картинке
 * @param amountCarb количество углеводов
 * @param amountPrt количество белков
 * @param amountFat количество жиров
 * @param expiryTimeDays срок годности в днях
 */
class DescriptionUIDto(
    @JsonProperty
    val title: String,
    @JsonProperty
    val description: String,
    @JsonProperty
    val urlImg: String,
    @JsonProperty
    val amountCarb: Double,
    @JsonProperty
    val amountPrt: Double,
    @JsonProperty
    val amountFat: Double,
    @JsonProperty
    val expiryTimeDays: Int,
)
