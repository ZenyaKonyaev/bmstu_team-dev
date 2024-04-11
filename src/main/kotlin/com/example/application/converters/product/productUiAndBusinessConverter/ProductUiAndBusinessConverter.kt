package com.example.application.converters.product.productUiAndBusinessConverter

import com.example.application.dto.product.ProductBusinessDto
import com.example.application.dto.product.ProductUIDto

interface ProductUiAndBusinessConverter {
    fun convert(businessDto: ProductBusinessDto): ProductUIDto

    fun convert(uiDto: ProductUIDto): ProductBusinessDto
}
