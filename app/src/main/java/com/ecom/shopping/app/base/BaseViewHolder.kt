package com.ecom.shopping.app.base

import android.arch.lifecycle.ViewModel
import com.ecom.shopping.app.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel constructor(val schedulers: SchedulerProvider): ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    protected fun getCompositeDisposable(): CompositeDisposable {
        return disposables
    }

    override fun onCleared() {
        disposables.clear()
    }

}