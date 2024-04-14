package com.example.application.exception

/**
 * Ошибка, полученная при обращении к базе данных
 * @param metaData описание ошибки
 */
open class DataBaseException(
    metaData: String?,
) : BaseException("MSG_DATABASE_EXCEPTION", metaData ?: "Database error")
