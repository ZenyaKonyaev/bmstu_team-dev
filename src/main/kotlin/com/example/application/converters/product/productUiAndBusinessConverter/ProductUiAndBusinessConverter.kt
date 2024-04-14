package com.example.application.converters.product.productUiAndBusinessConverter

import com.example.application.dto.product.ProductBusinessDto
import com.example.application.dto.product.ProductUIDto

/**
 * Интерфейс преобразования ui и бизнес сущности продукта
 */
interface ProductUiAndBusinessConverter {
    /**
     * преобразовать бизнес сущность в ui
     * @param businessDto бизнес сущность
     * @return ui сущность
     */
    fun convert(businessDto: ProductBusinessDto): ProductUIDto

    /**
     * преобразовать ui сущность в бизнес
     * @param uiDto ui сущнсть
     * @return бизнес сущность
     */
    fun convert(uiDto: ProductUIDto): ProductBusinessDto
}
