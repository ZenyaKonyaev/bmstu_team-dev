package com.example.application.dto.cakePart

import com.example.application.dto.description.DescriptionUIDto
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * UI сущность части торта
 * @param id идентификатор
 * @param name наименование части
 * @param cost стоимость части
 * @param type тип части
 * @param description описание части
 */
class CakePartUIDto(
    @JsonProperty
    var id: Long = 0L,
    @JsonProperty
    var name: String,
    @JsonProperty
    var cost: Double,
    @JsonProperty
    var type: Int,
    @JsonProperty
    var description: DescriptionUIDto = DescriptionUIDto("", "", "", 0.0, 0.0, 0.0, 0),
)
