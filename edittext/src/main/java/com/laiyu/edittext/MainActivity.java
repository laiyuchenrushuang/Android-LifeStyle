package com.laiyu.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.ReplacementTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intView();

        showSign();
    }

    private void showSign() {
        edit1.setError("输入不能为空", getDrawable(R.drawable.ic_launcher_background));


        edit3.setTransformationMethod(new Translate());
        edit3.setOnKeyListener((v, keyCode, event) -> {
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_ENTER: //两次监听
//                    Toast.makeText(MainActivity.this, edit3.getText().toString().toUpperCase(), Toast.LENGTH_SHORT).show();
//                    break;
//            }
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN://一次监听
                    Toast.makeText(MainActivity.this, edit3.getText().toString().toUpperCase(), Toast.LENGTH_SHORT).show();
                    edit2.requestFocus();
                    break;
            }
            return false;
        });
        edit4.setSelection(edit4.getText().toString().length());
        edit5.setInputType(InputType.TYPE_NULL);

        edit6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(MainActivity.this, "输入完成: " + s.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        edit7.setFilters(new InputFilter[]{new EmojiInputFilter()});

    }

    private void intView() {
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);
        edit5 = findViewById(R.id.edit5);
        edit6 = findViewById(R.id.edit6);
        edit7 = findViewById(R.id.edit7);
    }

    //大小写转换
    class Translate extends ReplacementTransformationMethod {
        @Override
        protected char[] getOriginal() {
            char[] aa = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            return aa;
        }

        @Override
        protected char[] getReplacement() {
            char[] cc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            return cc;
        }
    }

    //过滤表情
    public class EmojiInputFilter implements InputFilter {
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Matcher emojiMatcher = emoji.matcher(charSequence);
            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    }

}