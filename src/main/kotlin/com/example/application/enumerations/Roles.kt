package com.example.application.enumerations

/**
 * Перечисление ролей пользователя
 * @param role код роли
 */
enum class Roles(val role: String) {
    UNKNOWN("ROLE_UNKNOWN"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ;

    companion object {
        fun findRoleByString(role: String) = values().find { it.role == role } ?: UNKNOWN
    }
}
