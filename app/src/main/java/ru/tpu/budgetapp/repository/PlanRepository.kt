package ru.tpu.budgetapp.repository

import androidx.annotation.WorkerThread
import ru.tpu.budgetapp.api.plan.*
import ru.tpu.budgetapp.ui.UiPlan
import ru.tpu.budgetapp.ui.UiPlanItem
import java.time.LocalDate

class PlanRepository(
    val planApi: IPlanApi,
) {
    @WorkerThread
    fun getAllPlans(): List<UiPlan> {
        val data = planApi.getAllPlans().execute().body()!!.data

        val uiPlans = data.map { planDto: PlanDto ->
            return@map UiPlan(planDto)
        }

        return uiPlans
    }

    @WorkerThread
    fun createNewPlan(startDate: LocalDate): UiPlan {
        val data =
            planApi.createNewPlan(CreatePlanRequestDto(startDate)).execute().body()!!.data

        return UiPlan(data)
    }

    @WorkerThread
    fun deletePlan(id: Int) {
        planApi.deletePlan(id).execute()
    }

    @WorkerThread
    fun getAllItems(planId: Int): List<UiPlanItem> {
        val data = planApi.getAllPlanItems(planId).execute().body()!!.data

        val uiItems = data.map { planItemDto: PlanItemDto ->
            return@map UiPlanItem(planItemDto)
        }
        return uiItems
    }

    @WorkerThread
    fun createNewItem(planId: Int, value: Double, budgetItemId: Int): UiPlanItem {
        val data = planApi.createNewPlanItem(planId, CreatePlanItemRequestDto(value, budgetItemId))
            .execute().body()!!.data

        return UiPlanItem(data)
    }

    @WorkerThread
    fun deleteItem(id: Int) {
        planApi.deletePlanItem(id).execute()
    }
}