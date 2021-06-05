package com.laychv.ui_autofill

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.autofill.AutofillManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_autofill.*

class AutoFillActivity : AppCompatActivity() {

    private var afm: AutofillManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autofill)

        initView()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                afm = getSystemService(AutofillManager::class.java) as AutofillManager
                afm?.requestAutofill(tvUsername)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            afm?.registerCallback(afc)
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            afm?.unregisterCallback(afc)
        }
    }

    val afc = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        object : AutofillManager.AutofillCallback() {
            override fun onAutofillEvent(view: View, event: Int) {
                super.onAutofillEvent(view, event)
                when (event) {
                    EVENT_INPUT_UNAVAILABLE -> {
                        Log.d("aa","不可见")
                    }
                    EVENT_INPUT_HIDDEN -> {
                        Log.d("aa","隐藏")
                    }
                    EVENT_INPUT_SHOWN -> {
                        Log.d("aa","可见")
                    }
                    else -> {

                    }
                }
            }

            override fun onAutofillEvent(view: View, virtualId: Int, event: Int) {
                super.onAutofillEvent(view, virtualId, event)
            }
        }
    } else {
        TODO("VERSION.SDK_INT < O")
    }
}