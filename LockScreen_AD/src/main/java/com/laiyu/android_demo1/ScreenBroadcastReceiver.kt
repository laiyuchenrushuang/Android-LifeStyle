package com.laiyu.android_demo1

import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter


/**
 * Created by hdy on 2020/10/30 14:37
 */
class ScreenBroadcastReceiver(context: Context?) : BroadcastReceiver() {
    companion object {
        var mContext: Context? = null
    }


    init {
        if (mContext == null) {
            mContext = context
        }
    }

    private var isRegisterReceiver = false //是否注册广播

    var isLock = false
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent!!.action
        if (Intent.ACTION_SCREEN_ON == action) {
            // 开屏
            Uitls.showLog("onReceive: 1")


            //一个方案
            if (isLock) {
                val it = Intent()

                Uitls.showLog("开屏 启动")
                it.setClassName("com.laiyu.android_demo1", "com.laiyu.android_demo1.NextActivity");
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context!!.startActivity(it)
            }
            isLock = false

//            //二个方案
//            if (!ADService.isStarted) {
//                MyApp.mContext!!.startService(Intent(MyApp.mContext, ADService::class.java))
//            }

        } else if (Intent.ACTION_SCREEN_OFF == action) {
            // 锁屏
            Uitls.showLog("onReceive: 2")
            isLock = true

//            val km =
//                context!!.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//            Uitls.showLog(km.inKeyguardRestrictedInputMode())
//            if (km.inKeyguardRestrictedInputMode()) {
            // 处于锁屏状态

//            }


        } else if (Intent.ACTION_USER_PRESENT == action) {
            // 解锁
            Uitls.showLog("onReceive: 3")

        }
    }

    fun registerScreenBroadcastReceiver(mContext: Context) {
        if (!isRegisterReceiver) {
            isRegisterReceiver = true
            val filter = IntentFilter()
            filter.addAction(Intent.ACTION_SCREEN_OFF)
            filter.addAction(Intent.ACTION_SCREEN_ON)
            filter.addAction(Intent.ACTION_USER_PRESENT)
            mContext.registerReceiver(this@ScreenBroadcastReceiver, filter)
        }
    }

    fun unRegisterScreenBroadcastReceiver(mContext: Context) {
        if (isRegisterReceiver) {
            isRegisterReceiver = false
            mContext.unregisterReceiver(this@ScreenBroadcastReceiver)
        }
    }
}