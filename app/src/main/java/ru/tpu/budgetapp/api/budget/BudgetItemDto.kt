package ru.tpu.budgetapp.api.budget

import ru.tpu.budgetapp.api.category.CategoryDto

data class BudgetItemDto(
    val id: Int,
    val title: String,
    val category: CategoryDto,
)
