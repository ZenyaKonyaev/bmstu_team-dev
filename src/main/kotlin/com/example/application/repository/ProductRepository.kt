package com.example.application.repository

import com.example.application.entity.product.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

/**
 * JPA репозиторий продукта
 */
@Transactional
@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {
    /**
     * Получить продукт по его id
     * @param id id запрашиваемого продукта
     * @return бд сущность продукта
     */
    @Query("SELECT p FROM ProductEntity p WHERE p.id = ?1")
    fun getProductById(id: Long): ProductEntity

    /**
     * Получить все продукты
     * @return список бд сущностей всех возможных продуктов
     */
    @Query("FROM ProductEntity")
    fun getProductsCatalog(): List<ProductEntity>
}
