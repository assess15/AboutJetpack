package com.vaulert.arch_paging3

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vaulert.arch_paging3.paging3_room.ui.view.PagingRoomActivity

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun startPaging() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(appContext, PagingRoomActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appContext.startActivity(intent)
    }
}