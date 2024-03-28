package com.example.application.dto.cakePart

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.enumerations.CakePartType

interface CakePartBusinessDto {
    fun getId(): Long
    fun getName(): String
    fun getCost(): Double
    fun getType(): CakePartType
    fun getDescription(): DescriptionBusinessDto
}