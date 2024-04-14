package com.example.application.enumerations

/**
 * Перечисления тариффного плана пользователей
 * @param code код тарффного плана
 * @param planName наименование тариффиного плана
 */
enum class TariffPlan(val code: Int, val planName: String) {
    DEFAULT(1, "Массовый"),
    ;

    companion object {
        fun findTariffPlanByCode(code: Int) = values().find { it.code == code } ?: DEFAULT
    }
}
