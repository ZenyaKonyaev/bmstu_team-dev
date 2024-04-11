package com.example.application.converters.cakePart.cakePartEntityAndBusinessConverter

import com.example.application.converters.description.descriptionEntityAndBusinessConverter.DescriptionEntityAndBusinessConverter
import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.dto.cakePart.CakePartBusinessDtoImpl
import com.example.application.entity.cake_part.CakePartEntity
import com.example.application.enumerations.CakePartType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CakePartEntityAndBusinessConverterImpl : CakePartEntityAndBusinessConverter {
    @Autowired
    private lateinit var addDescrConverter: DescriptionEntityAndBusinessConverter

    override fun convert(dto: CakePartBusinessDto): CakePartEntity {
        return CakePartEntity(
            id = dto.getId(),
            name = dto.getName(),
            cost = dto.getCost(),
            type = dto.getType().type,
            addDescr = addDescrConverter.convert(dto.getDescription()),
        )
    }

    override fun convert(dto: CakePartEntity): CakePartBusinessDto {
        return CakePartBusinessDtoImpl(
            id = dto.id,
            name = dto.name,
            cost = dto.cost,
            type = CakePartType.getTypeCakeByType(dto.type),
            description = addDescrConverter.convert(dto.addDescr),
        )
    }
}
