package ru.tpu.budgetapp.ui.fragment

interface DatePickerWidget {
    fun onDateChange(year: Int, month: Int, dayOfMonth: Int): Unit
}