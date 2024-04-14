package com.example.application.services.product

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.dto.product.ProductBusinessDto

/**
 * Бизнес сервис по работе с продуктами
 */
interface ProductService {

    /**
     * Получить продукт по его id
     * @param id id запрашиваемого продукта
     *
     */
    fun getProductById(id: Long): ProductBusinessDto

    /**
     * Получить торт по идентификаторам его частей
     * @param idBase идентификатор основы
     * @param idFilling идентификатор начинки
     * @param idCream идентификатор крема
     * @return торт с запрашиваемыми иденитификаторами
     */
    fun getCakeByPartIds(
        idBase: Long,
        idFilling: Long,
        idCream: Long,
    ): CakeBusinessDto

    /**
     * Получить все возможные продукты
     * @return список бизнес-сущностей всех возможных продуктов
     */
    fun getProductCatalog(): List<ProductBusinessDto>

    /**
     * получить все возможные базовые части торта
     * @return список базовых частей торта
     */
    fun getCakeBaseParts(): List<CakePartBusinessDto>

    /**
     * получить все возможные начинки торта
     * @return список начинок торта
     */
    fun getCakeFillingParts(): List<CakePartBusinessDto>

    /**
     * получить все возможные крема торта
     * @return список кремов торта
     */
    fun getCakeCreamParts(): List<CakePartBusinessDto>
}
