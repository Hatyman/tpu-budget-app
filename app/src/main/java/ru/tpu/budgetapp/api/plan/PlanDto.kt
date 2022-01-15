package ru.tpu.budgetapp.api.plan

import java.time.LocalDate

data class PlanDto (
    val id: Int,
    val startDate: LocalDate,
    val accuracy: Double,
    val endDate: LocalDate,
)