package com.example.application.converters.bonus.bonusUiAndBusinessConverter

import com.example.application.dto.bonus.BonusBusinessDto
import com.example.application.dto.bonus.BonusUIDto

interface BonusUiAndBusinessConverter {
    fun convert(businessDto: BonusBusinessDto): BonusUIDto
}
