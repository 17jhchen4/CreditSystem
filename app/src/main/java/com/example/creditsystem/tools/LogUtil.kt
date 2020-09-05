package com.example.creditsystem.tools

import android.util.Log

/**
@author chenjinhui
@description: 自定义日志类
@date : 2020/8/30 20:45
 */
object LogUtil {

    private var isDebug = false

    fun v(tag: String, msg: String) {
        if (!isDebug) {
            Log.v(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (!isDebug) {
            Log.i(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (!isDebug) {
            Log.e(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (!isDebug) {
            Log.d(tag, msg)
        }
    }

}