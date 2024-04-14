package com.example.application.dto.user

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * UI сущеость пользователя
 * @param id идентификатор
 * @param name имя
 * @param surname фамилия
 * @param lastname отчество
 * @param address адрес
 * @param regDate дата регистрации
 * @param email почта
 */
data class UserUIDto(
    @JsonProperty
    val id: Long = 0L,
    @JsonProperty
    val name: String = "",
    @JsonProperty
    val surname: String = "",
    @JsonProperty
    val lastname: String = "",
    @JsonProperty
    val address: String = "",
    @JsonProperty
    val regDate: Date = Date(),
    @JsonProperty
    val email: String = "",
)
