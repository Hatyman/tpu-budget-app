package ru.tpu.budgetapp.presenter

import androidx.lifecycle.Lifecycle
import ru.tpu.budgetapp.App
import ru.tpu.budgetapp.repository.PlanRepository
import ru.tpu.budgetapp.ui.UiPlan

typealias PlansDataType = List<UiPlan>

class PlansPresenter(
    lifecycle: Lifecycle,
    widget: PresenterLoadIndicationWidget<PlansDataType>
) : AbstractPresenter<PlansDataType>(lifecycle, widget) {
    val planRepository: PlanRepository = App.scope.planRepository

    override fun requestData(): PlansDataType {
        return planRepository.getAllPlans()
    }
}