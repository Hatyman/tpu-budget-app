package ru.tpu.budgetapp.api.budget

import retrofit2.http.Body

class CreateBudgetItemRequestDto(
    val title: String,
    val categoryId: Int
)