package com.example.application.converters.cakePart.cakePartUiAndBusinessConverter

import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.dto.cakePart.CakePartUIDto

interface CakePartUiAndBusinessConverter {
    fun convert(businessDto: CakePartBusinessDto): CakePartUIDto

    fun convert(uiDto: CakePartUIDto): CakePartBusinessDto
}