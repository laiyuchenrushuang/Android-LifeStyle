package com.laiyu.androidlifetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.itsxtt.patternlock.PatternLockView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    PatternLockView patternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patternLockView = findViewById(R.id.patternLockView);
        patternLockView.setOnPatternListener(new PatternLockView.OnPatternListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(ArrayList<Integer> arrayList) {


            }

            @Override
            public boolean onComplete(ArrayList<Integer> arrayList) {

                String listString = arrayList.stream().map(Object::toString)
                        .collect(Collectors.joining(", "));

                showLog(listString);
                return false;
            }
        });

    }

    public void showLog(Object obj) {
        Log.d("【lylog】", "  " + obj);
    }
}