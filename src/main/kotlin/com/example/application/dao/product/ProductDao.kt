package com.example.application.dao.product

import com.example.application.dto.product.ProductBusinessDto

interface ProductDao {
    fun getProductById(id: Long): ProductBusinessDto

    fun getProductsCatalog(): List<ProductBusinessDto>

    fun addProduct(product: ProductBusinessDto)

    fun deleteProduct(id: Long)
}
