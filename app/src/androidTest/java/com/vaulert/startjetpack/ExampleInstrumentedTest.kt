package com.vaulert.startjetpack

import android.app.Instrumentation
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.vaulert.aboutjetpack", appContext.packageName)
    }


    /**
     * @link https://www.fengiling.com/topic/668906
     **/

    var mInstrumentation: Instrumentation? = null
    var mDevice: UiDevice? = null

    @Before
    fun setUp() {
        mInstrumentation = InstrumentationRegistry.getInstrumentation()
        mDevice = UiDevice.getInstance(mInstrumentation)
        mInstrumentation!!.targetContext
    }

    @Test
    fun testWx() {
        //按home键
        mDevice!!.pressHome()
        //启动微信
        val context = mInstrumentation?.context
        val intent = context?.packageManager?.getLaunchIntentForPackage("com.tencent.mm")
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context?.startActivity(intent)

        try {
            //点搜索按钮
            val searchButton = mDevice!!.findObject(UiSelector().description("搜索"))
            searchButton.click()
            //循环在搜索框里输入好友昵称
            val users = arrayListOf("会", "hui")
            for (user in users) {
                //从输入框输入昵称
                mDevice!!.findObject(UiSelector().resourceId("com.tencent.mm")).text = user
                val list = mDevice!!.findObject(UiSelector().className("android.widget.ListView"))
                val count = list.childCount
                //从找到的好友里找到好友对应的item
                val findItem = list.getChild(UiSelector().index(1))
                //点击进入聊天窗口
                findItem.click()
                //找于是聊天窗口的消息输入框并输入消息
                val msgContent = mDevice!!.findObject(UiSelector().resourceId("com.tencent.mm"))
                msgContent.text = " 我用外挂做测试"
                //点发送按钮
                val sendButton = mDevice!!.findObject(UiSelector().resourceId("com.tencent.mm"))
                sendButton.click()
                //返回到搜索界面
                mDevice!!.pressBack()
            }
        } catch (e: UiObjectNotFoundException) {
            e.printStackTrace()
        }
    }

    @After
    fun ddd(){

    }
}