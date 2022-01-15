package ru.tpu.budgetapp.api.budget

import ru.tpu.budgetapp.api.category.CategoryDto
import java.time.LocalDate

data class BudgetOperationDto(
    val id: Int,
    val date: LocalDate,
    val category: CategoryDto,
    val value: Double,
    val title: String
)
