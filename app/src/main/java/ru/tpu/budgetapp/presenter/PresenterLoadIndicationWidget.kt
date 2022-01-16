package ru.tpu.budgetapp.presenter

interface PresenterLoadIndicationWidget<T> {
    fun showLoading()
    fun hideLoading()
    fun showLoaded(data: T)
    fun showFailed()
}