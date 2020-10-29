package com.laiyu.scheme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by hdy on 2020/10/29 10:43
 */
public class NextActivity extends AppCompatActivity {
    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Intent intent = getIntent();
        String scheme = intent.getScheme();
        String dataString = intent.getDataString();
        Uri uri = intent.getData();
        if (uri != null) {
            //完整的url信息
            String url = uri.toString();
            //scheme部分
            String schemes = uri.getScheme();
            //host部分
            String host = uri.getHost();
            //port部分
            int port = uri.getPort();
            //访问路径
            String path = uri.getPath();
            //编码路径
            String path1 = uri.getEncodedPath();
            //query部分
            String queryString = uri.getQuery();
            //获取参数值
            String systemInfo = uri.getQueryParameter("tool_id");

            Log.d("lylog", "   " + systemInfo);

            TextView text = findViewById(R.id.text);
            String data = getResources().getString(R.string.receive);
            data = String.format(data, systemInfo);
            text.setText(data);
        }


    }
}
