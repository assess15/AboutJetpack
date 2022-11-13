package com.laychv.arch_lifecycle

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @des:LifecycleObserver观察者
 * @date:2022/11/13
 * @author:lay
 */
class TipDialog(context: Context) : Dialog(context), LifecycleObserver {

    init {
        if (context is ComponentActivity) {
            // 自动绑定被观察者
            context.lifecycle.addObserver(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_tip_dialog)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        Log.d("ddd", "onDestroy");
        if (isShowing) {
            dismiss()
        }
    }
}