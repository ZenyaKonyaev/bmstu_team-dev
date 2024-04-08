package com.example.application.exception

import java.util.*

open class BaseException(
    val code: String,
    val metaData: String,
    val timestamp: Date = Date()
): Exception(code)