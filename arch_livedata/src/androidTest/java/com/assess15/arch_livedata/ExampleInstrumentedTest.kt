package com.assess15.arch_livedata

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    /**
     * test LiveData
     */
    @Test
    fun startLiveData() {
        LiveDataUtil.getMLD().postValue("dddd")
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(appContext, LiveDataActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appContext.startActivity(intent)
    }
}