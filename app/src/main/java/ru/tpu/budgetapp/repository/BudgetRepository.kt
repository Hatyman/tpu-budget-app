package ru.tpu.budgetapp.repository

import androidx.annotation.WorkerThread
import ru.tpu.budgetapp.api.budget.*
import ru.tpu.budgetapp.ui.UiBudgetItem
import ru.tpu.budgetapp.ui.UiBudgetOperation
import ru.tpu.budgetapp.ui.UiBudgetState

class BudgetRepository(
    val budgetApi: IBudgetApi
) {
    @WorkerThread
    fun getAllOperations(): List<UiBudgetOperation> {
        val data = budgetApi.getAllOperations().execute().body()!!.data
        val uiOperations = data.map { budgetOperationDto: BudgetOperationDto ->
            return@map UiBudgetOperation(budgetOperationDto)
        }

        return uiOperations
    }

    @WorkerThread
    fun createNewOperation(value: Double, budgetItemId: Int): UiBudgetOperation {
        val data =
            budgetApi.createNewOperation(CreateBudgetOperationRequestDto(value, budgetItemId))
                .execute().body()!!.data

        return UiBudgetOperation(data)
    }

    @WorkerThread
    fun getAllItems(): List<UiBudgetItem> {
        val data = budgetApi.getAllItems().execute().body()!!.data
        val uiItems = data.map { budgetItem: BudgetItemDto ->
            return@map UiBudgetItem(budgetItem)
        }

        return uiItems
    }

    @WorkerThread
    fun createNewItem(title: String, categoryId: Int): UiBudgetItem {
        val data = budgetApi.createNewItem(CreateBudgetItemRequestDto(title, categoryId)).execute()
            .body()!!.data

        return UiBudgetItem(data)
    }

    @WorkerThread
    fun getState(): UiBudgetState {
        val data = budgetApi.getState().execute().body()!!.data
        return UiBudgetState(data)
    }

    @WorkerThread
    fun deleteOperation(id: Int) {
        budgetApi.deleteOperation(id).execute()
    }

    @WorkerThread
    fun deleteItem(id: Int) {
        budgetApi.deleteItem(id).execute()
    }
}