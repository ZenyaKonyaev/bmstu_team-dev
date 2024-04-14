package com.example.application.dto.cakePart

import com.example.application.dto.description.DescriptionBusinessDto
import com.example.application.enumerations.CakePartType


/**
 * Интерфейс бизнес-сущности части торта
 */
interface CakePartBusinessDto {

    /**
     * Получить идентификатор
     */
    fun getId(): Long

    /**
     * Получить имя части
     */

    fun getName(): String

    /**
     * Получить цену
     */

    fun getCost(): Double

    /**
     * Получить тип части
     */

    fun getType(): CakePartType

    /**
     * Получить описание части
     */

    fun getDescription(): DescriptionBusinessDto
}
