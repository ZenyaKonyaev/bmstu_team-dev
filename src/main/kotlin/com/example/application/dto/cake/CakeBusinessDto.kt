package com.example.application.dto.cake

import com.example.application.dto.cakePart.CakePartBusinessDto

/**
 * Интерфейс бизнес-сущности торта
 */
interface CakeBusinessDto {

    /**
     * Получить идентификатор
     */
    fun getId(): Long

    /**
     * Получить основу
     */
    fun getBase(): CakePartBusinessDto

    /**
     * Пролучить крем
     */
    fun getCream(): CakePartBusinessDto

    /**
     * Получить начинку
     */
    fun getFilling(): CakePartBusinessDto
}
