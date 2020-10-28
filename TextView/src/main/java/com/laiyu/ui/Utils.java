package com.laiyu.ui;

import android.content.Context;

/**
 * Created by hdy on 2020/10/28 17:39
 */
public class Utils {
    public static int dp2px(Context context, float dpValue) {
        int res = 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (dpValue < 0)
            res = -(int) (-dpValue * scale + 0.5f);
        else
            res = (int) (dpValue * scale + 0.5f);
        return res;
    }
}
