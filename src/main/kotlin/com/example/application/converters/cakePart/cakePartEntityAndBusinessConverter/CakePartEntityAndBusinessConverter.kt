package com.example.application.converters.cakePart.cakePartEntityAndBusinessConverter

import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.entity.cake_part.CakePartEntity

/**
 * Интерфейс преобразования бд и бизнес сущности части торта
 */
interface CakePartEntityAndBusinessConverter {

    /**
     * преобразовать бизнес сущность в бд
     * @param dto бизнес сущность
     * @return бд сущность
     */
    fun convert(dto: CakePartBusinessDto): CakePartEntity

    /**
     * преобразовать бд сущность и бизнес
     * @param dto бизнес сущность
     * @return бд сущность
     */
    fun convert(dto: CakePartEntity): CakePartBusinessDto
}
