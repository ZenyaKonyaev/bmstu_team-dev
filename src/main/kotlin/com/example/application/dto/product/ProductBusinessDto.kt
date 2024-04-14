package com.example.application.dto.product

import com.example.application.dto.bonus.BonusBusinessDto
import com.example.application.dto.description.DescriptionBusinessDto

/**
 * Интерфейс бизнес-сущности продукта
 */
interface ProductBusinessDto {
    /**
     * Получить идентификатор
     */
    fun getId(): Long
    /**
     * Получить наименование
     */

    fun getName(): String
    /**
     * Получить стоимость
     */

    fun getCost(): Double
    /**
     * Получить путь к картинке
     */

    fun getImgUrl(): String
    /**
     * Получить описание
     */

    fun getDescr(): DescriptionBusinessDto
    /**
     * Получить бонус
     */

    fun getBonuses(): List<BonusBusinessDto>?
}
