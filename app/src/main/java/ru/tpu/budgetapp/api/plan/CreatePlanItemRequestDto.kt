package ru.tpu.budgetapp.api.plan

data class CreatePlanItemRequestDto(
    val value: Double,
    val budgetItemId: Int,
)
