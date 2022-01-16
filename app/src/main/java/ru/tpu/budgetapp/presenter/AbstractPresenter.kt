package ru.tpu.budgetapp.presenter

import android.util.Log
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
    val widget: PresenterLoadIndicationWidget<T>,
    var doNotRequestIfDataCached: Boolean = false,
) : DefaultLifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        if (doNotRequestIfDataCached && data != null) {
            return
        }

        load()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycle.removeObserver(this)
    }

    var data: T? = null

    fun load() {
        if(data != null) {
            widget.showLoaded(data!!)
        }
        widget.showLoading()
        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            try {
                data = requestData()
                MainScope().launch {
                    widget.showLoaded(data!!)
                }
            } catch (e: Exception) {
                MainScope().launch {
                    widget.showFailed()
                }
            }
        }
    }

    fun doJobAndReloadData(function: () -> Unit) {
        widget.showLoading()
        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            try {
                function()
                data = requestData()
                MainScope().launch {
                    widget.showLoaded(data!!)
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