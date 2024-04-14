package com.example.application.converters.bonus.bonusUiAndBusinessConverter

import com.example.application.dto.bonus.BonusBusinessDto
import com.example.application.dto.bonus.BonusUIDto
import org.springframework.stereotype.Component


/**
 * Реализация интерфейса BonusUiAndBusinessConverter для типа BonusUIDto
 */
@Component
class BonusUiAndBusinessConverterImpl : BonusUiAndBusinessConverter {
    override fun convert(businessDto: BonusBusinessDto) =
        BonusUIDto(
            value = businessDto.getValue(),
            percentFlag = businessDto.getPercentFlag(),
            startDate = businessDto.getStartDate(),
            endDate = businessDto.getEndDate(),
        )
}
