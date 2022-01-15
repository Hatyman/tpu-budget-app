package ru.tpu.budgetapp.api.plan

import java.time.LocalDate

data class CreatePlanRequestDto(
    val startDate: LocalDate,
)
