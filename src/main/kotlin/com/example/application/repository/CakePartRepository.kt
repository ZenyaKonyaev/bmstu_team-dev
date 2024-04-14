package com.example.application.repository

import com.example.application.entity.cake_part.CakePartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

/**
 * JPA репозиторий части торта
 */
@Repository
@Transactional
interface CakePartRepository : JpaRepository<CakePartEntity, Long> {
    /**
     * Получить часть по идентификатору
     * @param id идентификатор
     * @return бд сущность части
     */
    @Query("SELECT p FROM CakePartEntity p WHERE p.id = ?1")
    fun getCakePartById(id: Long): CakePartEntity

    /**
     * Получить все основы
     * @return список всех основ торта
     */
    @Query("SELECT p FROM CakePartEntity p where p.type = 1")
    fun getCakeBaseParts(): List<CakePartEntity>

    /**
     * Получить все начинки
     * @return список всех начинок торта
     */
    @Query("SELECT p FROM CakePartEntity p where p.type = 2")
    fun getCakeFillingParts(): List<CakePartEntity>

    /**
     * Получить все крема
     * @return список всех кремов торта
     */
    @Query("SELECT p FROM CakePartEntity p where p.type = 3")
    fun getCakeCreamParts(): List<CakePartEntity>
}
