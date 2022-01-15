package ru.tpu.budgetapp.api.budget

import retrofit2.Call
import retrofit2.http.*
import ru.tpu.budgetapp.api.ApiResponse

interface IBudgetApi {
    @GET("/budget/")
    fun getState(): Call<ApiResponse<BudgetStateDto>>

    @GET("/budget/operations")
    fun getAllOperations(): Call<ApiResponse<List<BudgetOperationDto>>>

    @GET("/budget/items")
    fun getAllItems(): Call<ApiResponse<List<BudgetItemDto>>>

    @POST("/budget/items")
    fun createNewItem(@Body body: CreateBudgetItemRequestDto): Call<ApiResponse<BudgetItemDto>>

    @POST("/budget/operations")
    fun createNewOperation(@Body body: CreateBudgetOperationRequestDto): Call<ApiResponse<BudgetOperationDto>>

    @DELETE("/budget/operations/{id}")
    fun deleteOperation(
        @Path("id") id: Int
    ) : Call<Unit>

    @DELETE("/budget/items/{id}")
    fun deleteItem(
        @Path("id") id: Int
    ) : Call<Unit>
}