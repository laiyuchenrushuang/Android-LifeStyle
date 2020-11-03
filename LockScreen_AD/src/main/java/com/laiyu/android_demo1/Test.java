package com.laiyu.android_demo1;

import android.text.TextUtils;

import androidx.lifecycle.ViewModelProvider;

/**
 * Created by hdy on 2020/11/2 14:42
 */
class Test {
    public static void main(String[] args) {


        String str1 = "a<b";
        String str2 = "a<=b";
        String str3 = "c<=a<d";
        String str4 = "a>=e";

        String str11 = "(,b)";
        String str21 = "(,b]";
        String str31 = "[c,d)";
        String str41 = "[e,)";

        paseStr(str11);
        paseStr(str21);
        paseStr(str31);
        paseStr(str41);
    }

    private static void paseStr(String str1) {
        String str = str1.replace("(", "").replace(")", "").replace("[", "").replace("]", "");

        String[] newStr = str.split(",");

        if (str1.contains("]")) {
            showLog("小于等于" + newStr[1]);
            return;
        }
        if (str1.contains("[") && newStr.length == 2) {
            if (!"".equals(newStr[1]) && null != newStr[1]) {
                showLog("大于等于" + newStr[0] + "小于" + newStr[1]);
            }
            return;
        }
        if (str1.contains("[")) {
            showLog("大于等于" + newStr[0]);
            return;
        }
        showLog("小于" + newStr[1]);
    }

    public static void showLog(Object obj) {
        System.out.println(obj);
    }
}
