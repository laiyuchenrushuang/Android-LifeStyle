package com.seatrend.sharedpreference;

import android.app.Application;
import android.content.Context;

/**
 * Created by ly on 2020/11/9 11:42
 */
public class MyAPP extends Application {
    public static Context mCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        mCtx = this;
    }
}
