package com.example.application.dto.description

/**
 * Интерфейс бизнес-сущности описания
 */
interface DescriptionBusinessDto {
    /**
     * Получить идентификатор
     */
    fun getId(): Long

    /**
     * Получить заголовок
     */
    fun getTitle(): String

    /**
     * Получить описания
     */
    fun getDescription(): String

    /**
     * Получить путь к картинке
     */
    fun getUrlImg(): String

    /**
     * Получить количество углеводов
     */

    fun getAmountCarb(): Double

    /**
     * Получить количество протеинов
     */
    fun getAmountPrt(): Double

    /**
     * Получить количество жиров
     */
    fun getAmountFat(): Double

    /**
     * Получить срок годности
     */
    fun getExpiryTimeDays(): Int
}
