package ru.tpu.budgetapp.presenter

interface PresenterLoadIndicationWidget<T> {
    fun showLoading()
    fun showLoaded(data: T)
    fun showFailed()
}