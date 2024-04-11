package com.example.application.dto.rs

import com.example.application.enumerations.StatusRegistrationRs

data class RegistrationPageRs(
    val status: StatusRegistrationRs,
    val urlNextPage: String? = null,
)
