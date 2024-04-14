package com.example.application.converters.description.descriptionEntityAndBusinessConverter

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.entity.add_descr.DescrEntity

/**
 * Интерфейс преобразования бд и бизнес сущности описания
 */
interface DescriptionEntityAndBusinessConverter {
    /**
     * Преобразование бизнес сущности в бд
     * @param dto бизнес сущность
     * @return бд сущность
     */
    fun convert(dto: DescriptionBusinessDto): DescrEntity

    /**
     * Преобразование бд сущности в бизнес
     * @param dto бд сущность
     * @return бизнес сущность
     */
    fun convert(dto: DescrEntity): DescriptionBusinessDto
}
