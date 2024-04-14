package com.example.application.converters.description.descriptionEntityAndBusinessConverter

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.dto.description.DescriptionBusinessDtoImpl
import com.example.application.entity.add_descr.DescrEntity
import org.springframework.stereotype.Component

/**
 * Реализация DescriptionEntityAndBusinessConverter для типов DescrEntity и DescriptionBusinessDtoImpl
 */
@Component
class DescriptionEntityAndBusinessConverterImpl : DescriptionEntityAndBusinessConverter {
    override fun convert(dto: DescriptionBusinessDto): DescrEntity {
        return DescrEntity(
            id = dto.getId(),
            title = dto.getTitle(),
            descr = dto.getDescription(),
            imgName = dto.getUrlImg(),
            amountCarb = dto.getAmountCarb(),
            amountProt = dto.getAmountPrt(),
            amountFat = dto.getAmountFat(),
            expiryTimeDays = dto.getExpiryTimeDays(),
        )
    }

    override fun convert(dto: DescrEntity): DescriptionBusinessDto {
        return DescriptionBusinessDtoImpl(
            id = dto.id,
            title = dto.title,
            description = dto.descr,
            urlImg = dto.imgName,
            amountCarb = dto.amountCarb,
            amountPrt = dto.amountProt,
            amountFat = dto.amountFat,
            expiryTimeDays = dto.expiryTimeDays,
        )
    }
}
