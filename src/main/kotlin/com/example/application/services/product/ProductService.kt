package com.example.application.services.product

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.dto.product.ProductBusinessDto

interface ProductService {
    fun getProductById(id: Long): ProductBusinessDto

    fun getCakeByPartIds(
        idBase: Long,
        idFilling: Long,
        idCream: Long,
    ): CakeBusinessDto

    fun getAllCakes(): List<CakeBusinessDto>

    fun getProductCatalog(): List<ProductBusinessDto>

    fun getCakeBaseParts(): List<CakePartBusinessDto>

    fun getCakeFillingParts(): List<CakePartBusinessDto>

    fun getCakeCreamParts(): List<CakePartBusinessDto>

    fun addProduct(product: ProductBusinessDto)

    fun deleteProduct(productId: Long)

    fun addCakePart(cakePart: CakePartBusinessDto)

    fun deleteCakePart(cakePartId: Long)

    fun addCake(cakeDto: CakeBusinessDto)

    fun deleteCake(cakeId: Long)
}
