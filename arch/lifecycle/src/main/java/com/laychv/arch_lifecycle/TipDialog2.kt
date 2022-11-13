package com.laychv.arch_lifecycle

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @des:LifecycleEventObserver
 * @date:   2022/11/13
 * @author: lay
 */
class TipDialog2(context: Context) : Dialog(context), LifecycleEventObserver {

    init {
        if (context is ComponentActivity) {
            context.lifecycle.addObserver(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_tip_dialog)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_DESTROY -> {
                Log.d("ddd", "onDestroy");
                if (isShowing) dismiss()
            }
            else -> {}
        }
    }

}