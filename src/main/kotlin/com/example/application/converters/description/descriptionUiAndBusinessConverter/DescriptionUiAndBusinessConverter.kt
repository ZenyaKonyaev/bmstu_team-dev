package com.example.application.converters.description.descriptionUiAndBusinessConverter

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.dto.description.DescriptionUIDto

interface DescriptionUiAndBusinessConverter {
    fun convert(businessDto: DescriptionBusinessDto): DescriptionUIDto
}
