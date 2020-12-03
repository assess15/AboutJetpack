package com.vaulert.startjetpack

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun dte() {
        var transToString = transToString(1594051200000)
        var transToStrings = transToString(1594051200 * 1000)

        println(transToString)

        val transToTimeStamp = transToTimeStamp("2020-09-15")

        println(transToTimeStamp)
    }
}

fun transToString(time: Long): String {
    return SimpleDateFormat("yyyy-MM-dd").format(time)
}

fun transToTimeStamp(date: String): Long {
    return SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(date)!!.time
}