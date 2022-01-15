package ru.tpu.budgetapp.api.plan

import retrofit2.Call
import retrofit2.http.*
import ru.tpu.budgetapp.api.ApiResponse

interface IPlanApi {
    @GET("/plan/")
    fun getAllPlans(): Call<ApiResponse<List<PlanDto>>>

    @POST("/plan/")
    fun createNewPlan(@Body body: CreatePlanRequestDto): Call<ApiResponse<PlanDto>>

    @DELETE("/plan/{id}")
    fun deletePlan(@Path("id") id: Int) : Call<Unit>

    @GET("/plan/{planId}/items")
    fun getAllPlanItems(@Path("planId") planId: Int): Call<ApiResponse<List<PlanItemDto>>>

    @POST("/plan/{planId}/items")
    fun createNewPlanItem(
        @Path("planId") planId: Int,
        @Body body: CreatePlanItemRequestDto
    ): Call<ApiResponse<PlanItemDto>>

    @DELETE("/plan/items/{id}")
    fun deletePlanItem(@Path("id") id: Int) : Call<Unit>
}