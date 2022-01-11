package com.demo.lib_network

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.lib_network.net.HttpsURLConnectionHelp
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNetwork()
    }

    private fun initNetwork() {
//        GlobalScope.launch {
//            val request = HttpsURLConnectionHelp.requestByGetToString("https://www.baidu.com")
//            Log.d("net", request)
//        }
        MainScope().launch {
//            corout()
        }
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
//            net()
        }
        scope.cancel()

        val global = GlobalScope
        global.launch {
            more()
        }
        countDownCoroutines(20, {}, {}, global)
    }

    suspend fun works() {
        withContext(Dispatchers.IO) {
            more()
        }
    }

    suspend fun net() {
        coroutineScope {
            more()
        }
    }

    suspend fun corout() {
        coroutineScope {
            val work1 = async {
                Log.d("more", "hao shi cao zuo!")
            }
            val work = async {
                more()
            }
            work1.await()
            work.start()
        }
    }

    private fun more() {
        val request = HttpsURLConnectionHelp.requestByGetToString("https://www.baidu.com")
        Log.d("net", request)
    }
}