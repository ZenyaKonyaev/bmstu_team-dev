package com.example.application.dto.rq

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * ДТО с продуктом в заказе
 * @param intId идентификатор
 * @param name наименование
 * @param cost цена
 */
data class ProductDataElement(
    @JsonProperty("intId")
    val intId: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("cost")
    val cost: Double,
)

/**
 * ДТО с тортом в заказе
 * @param baseId идентификатор основы
 * @param fillingId идентификатор начинки
 * @param creamId идентификатор крема
 */
data class CakesDataElement(
    @JsonProperty("baseId")
    val baseId: Int,
    @JsonProperty("fillingId")
    val fillingId: Int,
    @JsonProperty("creamId")
    val creamId: Int,
)

/**
 * ДТО с запросом на создание заказа
 * @param productData продукты в заказе
 * @param cakesData торты в заказе
 */
data class RequestOrderDto(
    @JsonProperty("productData")
    val productData: List<ProductDataElement>,
    @JsonProperty("cakesData")
    val cakesData: List<CakesDataElement>,
)
