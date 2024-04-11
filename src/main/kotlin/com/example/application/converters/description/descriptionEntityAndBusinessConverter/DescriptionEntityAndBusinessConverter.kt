package com.example.application.converters.description.descriptionEntityAndBusinessConverter

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.entity.add_descr.DescrEntity

interface DescriptionEntityAndBusinessConverter {
    fun convert(dto: DescriptionBusinessDto): DescrEntity

    fun convert(dto: DescrEntity): DescriptionBusinessDto
}
