package com.example.application.exception

/**
 * Общая системная ошибка
 * @param metaData описание ошибки
 */
class UnavailableTechnicalException(
    metaData: String?,
) : BaseException("MSG_UNAVAILABLE_EXCEPTION", metaData ?: "Unavailable technical error")
