package ru.tpu.budgetapp.presenter

import androidx.lifecycle.Lifecycle
import ru.tpu.budgetapp.App
import ru.tpu.budgetapp.repository.BudgetRepository
import ru.tpu.budgetapp.ui.UiBudgetOperation

typealias BudgetOperationsDataType = List<UiBudgetOperation>

class BudgetOperationsPresenter(
    lifecycle: Lifecycle,
    widget: PresenterLoadIndicationWidget<BudgetOperationsDataType>
) : AbstractPresenter<BudgetOperationsDataType>(lifecycle, widget) {
    val budgetRepository: BudgetRepository = App.scope.budgetRepository

    override fun requestData(): BudgetOperationsDataType {
        return budgetRepository.getAllOperations()
    }
}