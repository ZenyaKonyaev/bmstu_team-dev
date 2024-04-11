package com.example.application.exception

class UnavailableTechnicalException(
    metaData: String?,
) : BaseException("MSG_UNAVAILABLE_EXCEPTION", metaData ?: "Unavailable technical error")
