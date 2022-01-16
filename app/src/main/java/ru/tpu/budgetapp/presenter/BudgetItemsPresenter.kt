package ru.tpu.budgetapp.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.tpu.budgetapp.App
import ru.tpu.budgetapp.repository.BudgetRepository
import ru.tpu.budgetapp.ui.UiBudgetItem

typealias BudgetItemsDataType = List<UiBudgetItem>

class BudgetItemsPresenter(
    lifecycle: Lifecycle,
    widget: PresenterLoadIndicationWidget<BudgetItemsDataType>
): AbstractPresenter<BudgetItemsDataType>(lifecycle, widget, true) {
    val budgetRepository: BudgetRepository = App.scope.budgetRepository

    override fun requestData(): BudgetItemsDataType {
        return budgetRepository.getAllItems()
    }
}