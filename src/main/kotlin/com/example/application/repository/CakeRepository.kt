package com.example.application.repository

import com.example.application.entity.cake_part.CakePartEntity
import com.example.application.entity.custom_cake.CakeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

/**
 * JPA репозиторий торта
 */
@Repository
@Transactional
interface CakeRepository : JpaRepository<CakeEntity, Long> {
    /**
     * Получить часть по идентификатору
     * @param id идентификатор
     * @return бд сущность части
     */
    @Query("SELECT p FROM CakePartEntity p WHERE p.id = ?1")
    fun getCakePartById(id: Long): CakePartEntity

    /**
     * Получить торт по идентификаторам его частей
     * @param idBase идентификатор основы
     * @param idFilling идентификатор начинки
     * @param idCream идентификатор крема
     * @return бд сущность торта
     */
    @Query("SELECT c FROM CakeEntity c WHERE c.basePart.id = ?1 and c.fillingPart.id = ?2 and c.creamPart = ?3")
    fun getCakeByPartByIds(
        idBase: Long,
        idFilling: Long,
        idCream: Long,
    ): CakePartEntity

    /**
     * Получить все торты
     * @return список всех бд сущностей тортов
     */
    @Query("FROM CakeEntity")
    fun getAllCakes(): List<CakeEntity>

    /**
     * Получить торт по его идентификатору
     * @param id идентификатор торта
     * @return бд сущность торта
     */
    @Query("SELECT c FROM CakeEntity c WHERE c.id = ?1")
    fun getCakeById(id: Long): CakeEntity

    /**
     * Получить стоимость торта по его идентификатору
     * @param id идентификатор торта
     * @return стоимость торта
     */
    @Query(value = "SELECT countCakeCost(?1)", nativeQuery = true)
    fun getCakeCostById(cakeId: Int): Double
}
