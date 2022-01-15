package ru.tpu.budgetapp.api.plan

import ru.tpu.budgetapp.api.category.CategoryDto

data class PlanItemDto(
    val id: Int,
    val title: String,
    val category: CategoryDto,
    val value: Double,
)