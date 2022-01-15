package ru.tpu.budgetapp.api

data class ApiResponse<T>(
    val code: Int,
    val data: T
)
