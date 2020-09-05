package com.example.creditsystem.tools

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
@author chenjinhui
@date : 2020/9/5 9:19
@description:Snackbar工具类
 */
object SnackBarUtil {

    fun makeSnackBar(view: View ,str: String) {
        Snackbar.make(view, str ,Snackbar.LENGTH_SHORT).show()
    }

    fun makeLongSnackBar(view: View ,str: String) {
        Snackbar.make(view, str ,Snackbar.LENGTH_LONG).show()
    }
}