package com.example.application.converters.product.productEntityAndBusinessConverter

import com.example.application.dto.product.ProductBusinessDto
import com.example.application.entity.product.ProductEntity

/**
 * Интерфейс преобразования бд и бизнес сущности продукта
 */
interface ProductEntityAndBusinessConverter {
    /**
     * преобразование бизнес сущности в бд
     * @param dto бизнес сущность
     * @return бд сущность
     */
    fun convert(dto: ProductBusinessDto): ProductEntity

    /**
     * преобразование бд в бизнес сущности
     * @param dto бд сущность
     * @return бизнес сущность
     */
    fun convert(dto: ProductEntity): ProductBusinessDto
}
