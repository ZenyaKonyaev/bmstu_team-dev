package com.example.application.converters.cake.cakeEntityAndBusinessConverter

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.entity.custom_cake.CakeEntity

/**
 * Интерфейс преобразования бизнес и бд сущности торта
 */
interface CakeEntityAndBusinessConverter {

    /**
     * Преобразовать бизнес сущеность в бд
     * @param dto бд сущность
     * @return бизнес сущность
     */
    fun convert(dto: CakeEntity): CakeBusinessDto

    /**
     * Преобразовать бд сущность в бизнес
     * @param dto бд сущность
     * @return бизнес сущность
     */
    fun convert(dto: CakeBusinessDto): CakeEntity
}
