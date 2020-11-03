package com.laiyu.android_demo1

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

/**
 * Created by hdy on 2020/10/30 14:26
 */
public class ScreenService : Service() {
    var mScreenReceiver: ScreenBroadcastReceiver? = null
    override fun onCreate() {
        super.onCreate()
        Uitls.showLog(" ScreenService 开启")
        registerGb()
    }

    override fun onDestroy() {
        super.onDestroy()
        Uitls.showLog(" ScreenService 关闭")
        mScreenReceiver!!.unRegisterScreenBroadcastReceiver(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun registerGb() {
        if (mScreenReceiver == null) {
            Uitls.showLog(" 广播接收开启")
            mScreenReceiver = ScreenBroadcastReceiver(this)
            mScreenReceiver!!.registerScreenBroadcastReceiver(this)
        }
    }
}