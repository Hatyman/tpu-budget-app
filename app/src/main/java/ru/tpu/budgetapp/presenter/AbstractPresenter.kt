package ru.tpu.budgetapp.presenter

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

abstract class AbstractPresenter<T : Any>(
    val lifecycle: Lifecycle,
    val widget: PresenterLoadIndicationWidget<T>
): DefaultLifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        load()
    }
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycle.removeObserver(this)
    }
    lateinit var data: T

    fun load() {
        widget.showLoading()
        lifecycle.coroutineScope.launch(Dispatchers.IO) {
//        GlobalScope.launch {
            try {
                data = requestData()
                MainScope().launch {
                    widget.showLoaded(data)
                }
            } catch (e: Exception) {
                MainScope().launch {
                    widget.showFailed()
                }
            }
        }
    }

    abstract protected fun requestData(): T
}