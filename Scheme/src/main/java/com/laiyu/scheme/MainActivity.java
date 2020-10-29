package com.laiyu.scheme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setText(Html.fromHtml("<a href=\"ceshi://ceshi:8080/loadpath?tool_id=100\">打开APP工具详情页</a>", Html.FROM_HTML_MODE_COMPACT));
    }

    public void onButtonClick(View view) {
        Intent action = new Intent(Intent.ACTION_VIEW);
        StringBuilder builder = new StringBuilder();
        builder.append("ceshi://ceshi:8080/loadpath?tool_id=Hello World");
        action.setData(Uri.parse(builder.toString()));
        startActivity(action);
    }
}