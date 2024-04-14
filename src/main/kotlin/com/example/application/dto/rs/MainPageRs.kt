package com.example.application.dto.rs

import com.example.application.dto.cakePart.CakePartUIDto
import com.example.application.dto.product.ProductUIDto
import com.example.application.dto.user.UserUIDto
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Информация с частями торта для главной страницы
 * @param bases основы тортов
 * @param fillings начинки тортов
 * @param creams крема тортов
 */
data class CakesFieldMainPageRs(
    @JsonProperty
    val bases: List<CakePartUIDto>,
    @JsonProperty
    val fillings: List<CakePartUIDto>,
    @JsonProperty
    val creams: List<CakePartUIDto>,
)

/**
 * ДТО с информацией о главной странице
 * @param products продукты
 * @param cakesParts части торта
 * @param user пользователь, который запрашивает страницу
 */
data class MainPageRs(
    @JsonProperty
    val products: List<ProductUIDto>,
    @JsonProperty
    val cakesParts: CakesFieldMainPageRs,
    @JsonProperty
    val user: UserUIDto?,
)
