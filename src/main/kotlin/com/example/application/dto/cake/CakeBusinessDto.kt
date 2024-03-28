package com.example.application.dto.cake

import com.example.application.dto.cakePart.CakePartBusinessDto

interface CakeBusinessDto {
    fun getId(): Long
    fun getBase(): CakePartBusinessDto
    fun getCream(): CakePartBusinessDto
    fun getFilling(): CakePartBusinessDto
}