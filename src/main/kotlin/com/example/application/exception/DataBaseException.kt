package com.example.application.exception

open class DataBaseException(
    metaData: String?,
) : BaseException("MSG_DATABASE_EXCEPTION", metaData ?: "Database error")
