package com.gavin.octopus

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

class OApplication : Application(){

    companion object{
        var applicationContext: Context? = null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        OApplication.applicationContext = base
    }

    override fun onCreate() {
        super.onCreate()

        //Test ARouter初始化暂先放在这里，优化方案：放在common，通过aop去通知common application onCreate
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}