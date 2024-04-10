package com.example.application.converters.product.productEntityAndBusinessConverter

import com.example.application.dto.product.ProductBusinessDto
import com.example.application.entity.product.ProductEntity

interface ProductEntityAndBusinessConverter {
    fun convert(dto: ProductBusinessDto): ProductEntity

    fun convert(dto: ProductEntity): ProductBusinessDto
}
