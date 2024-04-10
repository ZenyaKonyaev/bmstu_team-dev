package com.example.application.converters.cake.cakeEntityAndBusinessConverter

import com.example.application.converters.cakePart.cakePartEntityAndBusinessConverter.CakePartEntityAndBusinessConverter
import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cake.CakeBusinessDtoImpl
import com.example.application.entity.custom_cake.CakeEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CakeEntityAndBusinessConverterImpl : CakeEntityAndBusinessConverter {
    @Autowired
    private lateinit var cakePartConverter: CakePartEntityAndBusinessConverter

    override fun convert(dto: CakeEntity): CakeBusinessDto {
        return CakeBusinessDtoImpl(
            id = dto.id,
            base = cakePartConverter.convert(dto.basePart),
            filling = cakePartConverter.convert(dto.fillingPart),
            cream = cakePartConverter.convert(dto.creamPart),
        )
    }

    override fun convert(dto: CakeBusinessDto): CakeEntity {
        return CakeEntity(
            id = dto.getId(),
            basePart = cakePartConverter.convert(dto.getBase()),
            fillingPart = cakePartConverter.convert(dto.getFilling()),
            creamPart = cakePartConverter.convert(dto.getCream()),
        )
    }
}
