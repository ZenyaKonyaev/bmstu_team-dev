package com.example.application.dao.product

import com.example.application.dto.product.ProductBusinessDto

/**
 * Интерфейс компонента доступа к данным для продукта
 */
interface ProductDao {

    /**
     * Получить продукт по его идентификатору
     * @param id идентификатор запрашиваемого продукта
     * @return бизнес-сущность продукта
     */
    fun getProductById(id: Long): ProductBusinessDto

    /**
     * Получить все возможные продукты
     * @return список бизнес-сущностей всех возможных продуктов
     */
    fun getProductsCatalog(): List<ProductBusinessDto>

    /**
     * Добавить продукт в бд
     * @param product бизнес-сущность добавляемого продукта
     * @return ничего
     */
    fun addProduct(product: ProductBusinessDto)

    /**
     * Удалить продукт ищ бд
     * @param id идентификатор удаляемого продукта
     * @return ничего
     */
    fun deleteProduct(id: Long)
}
