package ru.tpu.budgetapp.api.plan

data class PlanDto (
    val id: Int,
    val startDate: String,
    val accuracy: Double,
    val endDate: String,
)