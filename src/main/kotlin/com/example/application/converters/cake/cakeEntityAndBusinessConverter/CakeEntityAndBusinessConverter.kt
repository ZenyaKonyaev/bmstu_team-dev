package com.example.application.converters.cake.cakeEntityAndBusinessConverter

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.entity.custom_cake.CakeEntity

interface CakeEntityAndBusinessConverter {
    fun convert(dto: CakeEntity): CakeBusinessDto

    fun convert(dto: CakeBusinessDto): CakeEntity
}
