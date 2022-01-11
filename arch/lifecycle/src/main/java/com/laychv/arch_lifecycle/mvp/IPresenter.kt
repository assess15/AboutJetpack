package com.laychv.arch_lifecycle.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class IPresenter(private val view: IView) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun show() {
        view.showView()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun hide() {
        view.hideView()
    }

}