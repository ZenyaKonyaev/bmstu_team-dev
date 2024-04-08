package com.example.application.converters.cakePart.cakePartEntityAndBusinessConverter

import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.entity.cake_part.CakePartEntity

interface CakePartEntityAndBusinessConverter {
    fun convert(dto: CakePartBusinessDto): CakePartEntity

    fun convert(dto: CakePartEntity): CakePartBusinessDto
}