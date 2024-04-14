package com.example.application.converters.cakePart.cakePartUiAndBusinessConverter

import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.dto.cakePart.CakePartUIDto

/**
 * Интерфейс преобразования UI и бизнес сущности части торта
 */
interface CakePartUiAndBusinessConverter {

    /**
     * преобразовать бизнес сущность в ui
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: CakePartBusinessDto): CakePartUIDto

    /**
     * преобразовать ui сущеость в бизнес
     * @param uiDto ui сущность
     * @return бизнес сущность
     */
    fun convert(uiDto: CakePartUIDto): CakePartBusinessDto
}
