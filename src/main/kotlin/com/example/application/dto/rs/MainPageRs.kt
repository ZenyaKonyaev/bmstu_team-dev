package com.example.application.dto.rs

import com.example.application.dto.cakePart.CakePartUIDto
import com.example.application.dto.product.ProductUIDto
import com.example.application.dto.user.UserUIDto
import com.fasterxml.jackson.annotation.JsonProperty

data class CakesFieldMainPageRs(
    @JsonProperty
    val bases: List<CakePartUIDto>,
    @JsonProperty
    val fillings: List<CakePartUIDto>,
    @JsonProperty
    val creams: List<CakePartUIDto>
)

data class MainPageRs(
    @JsonProperty
    val products: List<ProductUIDto>,
    @JsonProperty
    val cakesParts: CakesFieldMainPageRs,
    @JsonProperty
    val user: UserUIDto?,
)