package com.laiyu.android_demo1

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.*
import android.widget.Button

/**
 * Created by hdy on 2020/10/30 15:10
 */
class ADService : Service() {
    companion object {
        var isStarted = false
    }

    private var windowManager: WindowManager? = null
    private var layoutParams: WindowManager.LayoutParams? = null

    private var displayView: View? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        isStarted = true
        Uitls.showLog(" ADService 开启")
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        layoutParams = WindowManager.LayoutParams()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams!!.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            layoutParams!!.type = WindowManager.LayoutParams.TYPE_PHONE
        }
        layoutParams!!.format = PixelFormat.RGBA_8888
        layoutParams!!.gravity = Gravity.LEFT or Gravity.TOP
        layoutParams!!.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//        layoutParams!!.width = 100
//        layoutParams!!.height = 200
//        layoutParams!!.x = 300
//        layoutParams!!.y = 300
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showFloatingWindow()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showFloatingWindow() {

        Uitls.showLog(Settings.canDrawOverlays(this))
        if (Settings.canDrawOverlays(this)) {
            val layoutInflater = LayoutInflater.from(this)
            displayView = layoutInflater.inflate(R.layout.app_display, null)
            var btn = displayView!!.findViewById<Button>(R.id.btn)
            btn.setOnClickListener {
                windowManager!!.removeView(displayView)
            }
            windowManager!!.addView(displayView, layoutParams)
        }
    }
}