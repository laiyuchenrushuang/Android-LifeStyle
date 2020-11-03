package com.laiyu.android_demo1

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult


/**
 * Created by hdy on 2020/10/30 14:20
 */
class MyApp : Application() {
    companion object {
        var mContext: Context? = null
    }



    override fun onCreate() {
        super.onCreate()
        mContext = this

        startSService()

    }





    private fun startSService() {
        val intent = Intent(this, ScreenService::class.java)
        intent.action = "OPEN_SCREEN_SERVICE"
        startService(intent)
    }
}