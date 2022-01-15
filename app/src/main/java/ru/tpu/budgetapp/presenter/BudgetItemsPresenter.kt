package ru.tpu.budgetapp.presenter

import androidx.lifecycle.Lifecycle
import ru.tpu.budgetapp.App
import ru.tpu.budgetapp.repository.BudgetRepository
import ru.tpu.budgetapp.ui.UiBudgetItem

typealias BudgetItemsDataType = List<UiBudgetItem>

class BudgetItemsPresenter(
    lifecycle: Lifecycle,
    widget: PresenterLoadIndicationWidget<BudgetItemsDataType>
): AbstractPresenter<BudgetItemsDataType>(lifecycle, widget) {
    val budgetRepository: BudgetRepository = App.scope.budgetRepository

    override fun requestData(): BudgetItemsDataType {
        return budgetRepository.getAllItems()
    }
}