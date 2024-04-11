package com.example.application.converters.cake.cakeUiAndBusinessConverter

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cake.CakeUIDto

interface CakeUiAndBusinessConverter {
    fun convert(businessDto: CakeBusinessDto): CakeUIDto

    fun convert(uiDto: CakeUIDto): CakeBusinessDto
}
