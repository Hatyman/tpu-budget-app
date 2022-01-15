package ru.tpu.budgetapp.presenter

import androidx.lifecycle.Lifecycle
import ru.tpu.budgetapp.App
import ru.tpu.budgetapp.repository.PlanRepository
import ru.tpu.budgetapp.ui.UiPlanItem

typealias PlanItemsDataType = List<UiPlanItem>

class PlanItemsPresenter(
    val planId: Int,
    lifecycle: Lifecycle,
    widget: PresenterLoadIndicationWidget<PlanItemsDataType>,
) : AbstractPresenter<PlanItemsDataType>(lifecycle, widget) {
    val planRepository: PlanRepository = App.scope.planRepository

    override fun requestData(): PlanItemsDataType {
        return planRepository.getAllItems(planId)
    }
}