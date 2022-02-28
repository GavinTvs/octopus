package com.gavin.common.utils

import android.util.Log


fun printLog(
    msg: Any
){
    when (msg) {
        is String -> print(msg)
//        is List<*> -> printLog(msg.toString())
        else -> print(msg.toString())
    }
}

private fun print(msg: String, throwable: Throwable? = null){
    Log.d("", msg, throwable)
}