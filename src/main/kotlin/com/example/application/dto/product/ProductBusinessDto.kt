package com.example.application.dto.product

import com.example.application.dto.bonus.BonusBusinessDto
import com.example.application.dto.description.DescriptionBusinessDto

interface ProductBusinessDto {
    fun getId(): Long

    fun getName(): String

    fun getCost(): Double

    fun getImgUrl(): String

    fun getDescr(): DescriptionBusinessDto

    fun getBonuses(): List<BonusBusinessDto>?
}
