package com.laychv.arch_hilt.simple

import javax.inject.Inject

class SimpleInject @Inject constructor() {
    fun doSomethings(): String {
        return "我把这段代码注入过来!!!!"
    }
}