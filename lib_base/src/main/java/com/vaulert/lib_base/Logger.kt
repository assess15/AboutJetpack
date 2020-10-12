package com.vaulert.lib_base

import android.util.Log

object Logger {

    private var DEFAULT_TAG = "tag"

    fun init(tag: String) {
        this.DEFAULT_TAG = tag
    }

    fun i(tag: String, msg: String) {
        if (Constant.DEBUG)
            Log.i(tag, msg)
    }

    fun v(tag: String, msg: String) {
        if (Constant.DEBUG)
            Log.v(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (Constant.DEBUG)
            Log.d(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (Constant.DEBUG)
            Log.e(tag, msg)
    }

    fun i(msg: String) {
        i(DEFAULT_TAG, msg)
    }

    fun v(msg: String) {
        v(DEFAULT_TAG, msg)
    }

    fun d(msg: String) {
        d(DEFAULT_TAG, msg)
    }

    fun e(msg: String) {
        e(DEFAULT_TAG, msg)
    }

}