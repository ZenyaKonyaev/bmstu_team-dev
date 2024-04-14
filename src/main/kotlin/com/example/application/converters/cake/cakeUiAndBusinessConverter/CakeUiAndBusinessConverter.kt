package com.example.application.converters.cake.cakeUiAndBusinessConverter

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cake.CakeUIDto

/**
 * Интерфейс преобразования UI и бизнес сущности торта
 */
interface CakeUiAndBusinessConverter {

    /**
     * Преобразовать бизнес сущность в ui
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: CakeBusinessDto): CakeUIDto

    /**
     * Преобразовать ui сущность в бизнес
     * @param uiDto ui сущность
     * @return бизнес сущность
     */
    fun convert(uiDto: CakeUIDto): CakeBusinessDto
}
