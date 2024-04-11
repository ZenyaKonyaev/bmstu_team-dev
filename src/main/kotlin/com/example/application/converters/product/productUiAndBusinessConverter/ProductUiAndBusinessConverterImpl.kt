package com.example.application.converters.product.productUiAndBusinessConverter

import com.example.application.converters.bonus.bonusUiAndBusinessConverter.BonusUiAndBusinessConverter
import com.example.application.converters.description.descriptionUiAndBusinessConverter.DescriptionUiAndBusinessConverter
import com.example.application.dto.description.DescriptionBusinessDtoImpl
import com.example.application.dto.product.ProductBusinessDto
import com.example.application.dto.product.ProductBusinessDtoImpl
import com.example.application.dto.product.ProductUIDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductUiAndBusinessConverterImpl : ProductUiAndBusinessConverter {
    @Autowired
    private lateinit var descriptionConverter: DescriptionUiAndBusinessConverter

    @Autowired
    private lateinit var bonusConverter: BonusUiAndBusinessConverter

    override fun convert(businessDto: ProductBusinessDto) =
        ProductUIDto(
            id = businessDto.getId(),
            name = businessDto.getName(),
            cost = businessDto.getCost(),
            urlImg = businessDto.getImgUrl(),
            description = descriptionConverter.convert(businessDto.getDescr()),
            bonuses = businessDto.getBonuses()?.map { bonusConverter.convert(it) },
        )

    override fun convert(uiDto: ProductUIDto) =
        ProductBusinessDtoImpl(
            name = uiDto.name,
            cost = uiDto.cost,
            imageUrl = uiDto.urlImg,
            addDescr = DescriptionBusinessDtoImpl(1, "test_title", "test_description", "test_url_img"),
        )
}
