package com.demo.lib_network

import com.demo.lib_network.cache.LruCache
import com.demo.lib_network.cache.LruCache2
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val cache = LruCache<Int, String>(2)
        cache.put(0, "这是第0个")
        cache.put(1, "这是第1个")
        cache.get(0)
        println("${cache.get(0)}")
        cache.put(2, "这是第2个")
        cache.get(0)
        println("${cache.get(0)}")

        val cac = LruCache2(2)
        cac.put(0, 1)
        cac.put(1, 2)
        println("打印的东西: " + "${cac.get(0)}")
    }
}