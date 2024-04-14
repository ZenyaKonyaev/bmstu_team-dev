package com.example.application.exception

import java.util.*

/**
 * Базовый класс ошибки
 * @param code код ошибки
 * @param metaData описание ошибки
 * @param timestamp время ошибки
 */
open class BaseException(
    val code: String,
    val metaData: String,
    val timestamp: Date = Date(),
) : Exception(code)
