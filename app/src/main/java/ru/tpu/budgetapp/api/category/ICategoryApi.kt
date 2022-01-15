package ru.tpu.budgetapp.api.category

import retrofit2.Call
import retrofit2.http.GET
import ru.tpu.budgetapp.api.ApiResponse

interface ICategoryApi {
    @GET("/category/")
    fun getAllCategories(): Call<ApiResponse<List<CategoryDto>>>
}