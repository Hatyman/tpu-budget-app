package ru.tpu.budgetapp.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.tpu.budgetapp.api.category.ICategoryApi
import ru.tpu.budgetapp.ui.UiCategory

class CategoryRepository(
    val categoryApi: ICategoryApi
) {
    lateinit var cachedCategories: List<UiCategory>

    @WorkerThread
    fun getAllCategories(): List<UiCategory> {
        val data = categoryApi.getAllCategories().execute().body()!!.data

        cachedCategories = data.map { categoryDto ->
            UiCategory(categoryDto)
        }
        return cachedCategories
    }
}