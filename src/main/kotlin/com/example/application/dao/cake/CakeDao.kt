package com.example.application.dao.cake

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cakePart.CakePartBusinessDto

/**
 * Интерфейс компонента доступа к данным для торта
 */
interface CakeDao {

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

    /**
     * Получить часть торта по ее идентификатору бд
     * @param id идентификатор части
     * @return часть торта
     */
    fun getCakePartById(id: Long): CakePartBusinessDto

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
     * Получить все возможные торты
     * @return список всех возможных тортов
     */
    fun getAllCakes(): List<CakeBusinessDto>

    /**
     * Добавить часть торта в бд
     * @param dto добавляемая часть торта
     * @return ничего
     */
    fun addCakePart(dto: CakePartBusinessDto)

    /**
     * Удалить часть торта по id
     * @param id идентификатор удаляемой части торта
     * @return ничего
     */
    fun deleteCakePart(id: Long)

    /**
     * Добавить торт в бд
     * @param dto добавляемый торт
     * @return ничего
     */
    fun addCake(dto: CakeBusinessDto)

    /**
     * Удалить торт по id
     * @param id идентификатор удаляемого торта
     * @return ничего
     */
    fun deleteCake(id: Long)
}
