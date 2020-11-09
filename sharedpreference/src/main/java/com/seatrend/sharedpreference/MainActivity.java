package com.seatrend.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edit1;
    EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
    }

    public void save(View view) {
        SharedPerfrenceUtils.save("edit1", edit1.getText().toString());
    }

    public void read(View view) {
        edit2.setText(SharedPerfrenceUtils.get("edit1"));
    }

    public void deleteALL(View view) {
        SharedPerfrenceUtils.deleteAll();
    }
}