package com.example.application.services.product

import com.example.application.dao.cake.CakeDao
import com.example.application.dao.product.ProductDao
import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.dto.product.ProductBusinessDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Реализация ProductService
 */
@Service
class ProductServiceImpl : ProductService {
    @Autowired
    private lateinit var productDao: ProductDao

    @Autowired
    private lateinit var cakeDao: CakeDao

    override fun getProductById(id: Long) = productDao.getProductById(id)

    override fun getCakeByPartIds(
        idBase: Long,
        idFilling: Long,
        idCream: Long,
    ) = cakeDao.getCakeByPartIds(
        idBase,
        idFilling,
        idCream,
    )

    override fun getProductCatalog(): List<ProductBusinessDto> {
        val start = System.nanoTime()
        val res = productDao.getProductsCatalog()
        val end = System.nanoTime()
        return res
    }

    override fun getCakeBaseParts(): List<CakePartBusinessDto> {
        val start = System.nanoTime()
        val res = cakeDao.getCakeBaseParts()
        val end = System.nanoTime()
        return res
    }

    override fun getCakeFillingParts(): List<CakePartBusinessDto> {
        val start = System.nanoTime()
        val res = cakeDao.getCakeFillingParts()
        val end = System.nanoTime()
        return res
    }

    override fun getCakeCreamParts(): List<CakePartBusinessDto> {
        val start = System.nanoTime()
        val res = cakeDao.getCakeCreamParts()
        val end = System.nanoTime()
        return res
    }
}
