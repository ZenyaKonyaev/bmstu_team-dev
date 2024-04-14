package com.example.application.converters.bonus.bonusUiAndBusinessConverter

import com.example.application.dto.bonus.BonusBusinessDto
import com.example.application.dto.bonus.BonusUIDto

/**
 * Конвертер UI и бизнес сущеости бонуса
 */
interface BonusUiAndBusinessConverter {

    /**
     * Преобразовать бизнес сущность бонуса в ui сущность
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: BonusBusinessDto): BonusUIDto
}
