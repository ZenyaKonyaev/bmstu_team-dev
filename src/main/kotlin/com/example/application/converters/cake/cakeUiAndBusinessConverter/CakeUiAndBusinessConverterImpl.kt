package com.example.application.converters.cake.cakeUiAndBusinessConverter

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cake.CakeBusinessDtoImpl
import com.example.application.dto.cake.CakeUIDto
import com.example.application.converters.cakePart.cakePartUiAndBusinessConverter.CakePartUiAndBusinessConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CakeUiAndBusinessConverterImpl: CakeUiAndBusinessConverter {
    @Autowired
    private lateinit var partConverter: CakePartUiAndBusinessConverter

    override fun convert(businessDto: CakeBusinessDto) = CakeUIDto(
        idCake = businessDto.getId(),
        base = partConverter.convert(businessDto.getBase()),
        filling = partConverter.convert(businessDto.getFilling()),
        cream = partConverter.convert(businessDto.getCream()),
        totalCost = 100.0//TODO получать опцию стоимости из репозитория
    )

    override fun convert(uiDto: CakeUIDto) = CakeBusinessDtoImpl(
        base = partConverter.convert(uiDto.base),
        filling = partConverter.convert(uiDto.filling),
        cream = partConverter.convert(uiDto.cream)
    )

}