package com.seatrend.sharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by ly on 2020/11/9 11:42
 *
 *
 * 可以储存的数据类型有：boolean,int,float,long,String
 *
 * 文件路径 "/data/data/"
 *             + context.getPackageName() + "/shared_prefs"
 *
 */
public class SharedPerfrenceUtils {
    public static String P_KEY = "p_key"; //存储某一类的信息 键类型  方便以后全部删除
    static private String DEFUALT = "默认值"; //为空取默认值

    static private SharedPreferences spBuild = MyAPP.mCtx.getSharedPreferences(P_KEY, Activity.MODE_PRIVATE);

    static SharedPreferences.Editor editor = spBuild.edit();

    public static void save(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static String get(String key) {
        return spBuild.getString(key, DEFUALT);
    }

    public static void delete(String key) {
        editor.remove(key);
        editor.apply();
    }

    public static void deleteAll() {
        editor.clear();
        editor.apply();
    }
}
