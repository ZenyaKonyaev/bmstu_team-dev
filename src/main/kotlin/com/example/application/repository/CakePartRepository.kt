package com.example.application.repository

import com.example.application.entity.cake_part.CakePartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface CakePartRepository : JpaRepository<CakePartEntity, Long> {
    @Query("SELECT p FROM CakePartEntity p WHERE p.id = ?1")
    fun getCakePartById(id: Long): CakePartEntity

    @Query("SELECT p FROM CakePartEntity p where p.type = 1")
    fun getCakeBaseParts(): List<CakePartEntity>

    @Query("SELECT p FROM CakePartEntity p where p.type = 2")
    fun getCakeFillingParts(): List<CakePartEntity>

    @Query("SELECT p FROM CakePartEntity p where p.type = 3")
    fun getCakeCreamParts(): List<CakePartEntity>
}
