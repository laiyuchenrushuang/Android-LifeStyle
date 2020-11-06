package com.laiyu.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView current_battery;
    BatteryReceiver batteryReceiver = new BatteryReceiver();
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        current_battery = findViewById(R.id.current_battery);
        register();
    }

    private void register() {

        IntentFilter intent = new IntentFilter();
        intent.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, intent);
    }

    private void unRegister() {
        unregisterReceiver(batteryReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
    }

    public static class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null && Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                StringBuffer sb = new StringBuffer();

                int level = intent.getIntExtra("level", 0);//当前电量
                int scale = intent.getIntExtra("scale", 0);//电量范围
                int voltage = intent.getIntExtra("voltage", 0);//电池伏数
                int temperature = intent.getIntExtra("temperature", 0);//电池温度
                //电池状态，返回是一个数字
                // BatteryManager.BATTERY_STATUS_CHARGING 表示是充电状态
                // BatteryManager.BATTERY_STATUS_DISCHARGING 放电中
                // BatteryManager.BATTERY_STATUS_NOT_CHARGING 未充电
                // BatteryManager.BATTERY_STATUS_FULL 电池满
                int status = intent.getIntExtra("status", 0);

                //充电类型 BatteryManager.BATTERY_PLUGGED_AC 表示是充电器，不是这个值，表示是 USB
                int plugged = intent.getIntExtra("plugged", 0);

                //电池健康情况，返回也是一个数字
                // BatteryManager.BATTERY_HEALTH_GOOD 良好
                //BatteryManager.BATTERY_HEALTH_OVERHEAT 过热
                //BatteryManager.BATTERY_HEALTH_DEAD 没电
                //BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE 过电压
                //BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE 未知错误
                int health = intent.getIntExtra("health", 0);

                //++++++++++

                int battery_low = intent.getIntExtra("battery_low", 0);
                int present = intent.getIntExtra("present", 0);
                int icon_small = intent.getIntExtra("icon-small", 0);
                int technology = intent.getIntExtra("technology", 0);
                int invalid_charger = intent.getIntExtra("invalid_charger", 0);
                int max_charging_current = intent.getIntExtra("max_charging_current", 0);

                sb.append("计算次数 = ").append(count++).append("\n");
                sb.append("level = ").append(level).append("\n");
                sb.append("scale = ").append(scale).append("\n");
                sb.append("voltage = ").append(voltage).append("\n");
                sb.append("temperature = ").append(temperature).append("\n");
                sb.append("status = ").append(status).append("\n");
                sb.append("plugged = ").append(plugged).append("\n");
                sb.append("health = ").append(health).append("\n");
                sb.append("battery_low = ").append(battery_low).append("\n");
                sb.append("present = ").append(present).append("\n");
                sb.append("icon-small = ").append(icon_small).append("\n");
                sb.append("invalid_charger = ").append(invalid_charger).append("\n");
                sb.append("max_charging_current = ").append(max_charging_current).append("\n");

                current_battery.setText(context.getString(R.string.current_battery, sb.toString()));
            }

        }
    }

    public static void showLog(Object s) {
        Log.d("[lylog]", "   " + s);
    }
}