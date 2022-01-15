package ru.tpu.budgetapp.api.budget

data class CreateBudgetOperationRequestDto(
    val value: Double,
    val budgetItemId: Int,
)
