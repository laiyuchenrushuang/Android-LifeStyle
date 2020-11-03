package com.laiyu.android_demo1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // 设置点亮屏幕


        super.onCreate(savedInstanceState)

        val win: Window = window
        win.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        Uitls.showLog("MainActivity 被创建")

        setContentView(R.layout.activity_main)
        canDisplay()

    }

    override fun onDestroy() {
        super.onDestroy()
        Uitls.showLog("MainActivity 被销毁")
    }

    @SuppressLint("InvalidWakeLockTag")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val pm =
            this.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!pm.isScreenOn) {
            val wl = pm.newWakeLock(
                (PowerManager.ACQUIRE_CAUSES_WAKEUP or
                        PowerManager.SCREEN_BRIGHT_WAKE_LOCK), "bright"
            )
            wl.acquire()
            wl.release()
        }
    }

    fun btn(view: View) {
        MyApp.mContext!!.startService(Intent(MyApp.mContext, ADService::class.java))
    }

    private fun canDisplay() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT)
            startActivityForResult(
                Intent(
                    ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                ), 0
            )
        }
    }
}