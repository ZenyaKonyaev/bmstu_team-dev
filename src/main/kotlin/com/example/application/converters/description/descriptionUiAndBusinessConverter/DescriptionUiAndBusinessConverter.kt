package com.example.application.converters.description.descriptionUiAndBusinessConverter

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.dto.description.DescriptionUIDto

/**
 * Интерфейс преобразования ui и бизнес сущности описания
 */
interface DescriptionUiAndBusinessConverter {
    /**
     * Преобразовать сущность бд в сущность ui
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: DescriptionBusinessDto): DescriptionUIDto
}
