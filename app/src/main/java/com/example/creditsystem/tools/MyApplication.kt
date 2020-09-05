package com.example.creditsystem.tools

import android.app.Application
import android.content.Context

/**
@author chenjinhui
@description: 用于全局获取content
 */
class MyApplication : Application(){

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}