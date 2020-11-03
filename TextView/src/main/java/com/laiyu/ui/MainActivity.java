package com.laiyu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.AssetManager;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.ScaleXSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private TextView text8;
    private TextView text9;
    private TextView text10;
    private TextView text11;
    private TextView text12;
    private ExpandableTextView text13;
    private TextView text14;
    boolean isExpand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();

        setStyle();
    }

    private void setStyle() {

        text1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);


        String str1 = "今天<font color= \"#ff0000\">天气不错</font>";
        text2.setText(Html.fromHtml(str1, Html.FROM_HTML_MODE_LEGACY));


        SpannableString sp = new SpannableString("天气不错");
        Typeface tf = Typeface.createFromAsset(getAssets(), "font/SIMLI.TTF");
        text3.append(sp);
        text3.setTypeface(tf);


//        String str2 = "<div style=\"font-family:Microsoft YaHei\">天气不错</div>";
//        text3.setText(Html.fromHtml(str2));

        text4.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        text4.getPaint().setAntiAlias(true);//抗锯齿
        text5.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        text5.getPaint().setAntiAlias(true);//抗锯齿


        String str3 = "<font color= \"#ff0000\">天气<sub>不错</sub></font>";
        text6.setText(Html.fromHtml(str3, Html.FROM_HTML_MODE_LEGACY));


        SpannableString span = new SpannableString("天气不错");
        span.setSpan(new SuperscriptSpan(), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标 (2,4]
        text7.append(span);


        SpannableString span1 = new SpannableString("天气不错");
        span1.setSpan(new ScaleXSpan(2.0f), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//X轴方向
        text8.append(span1);


        SpannableString span2 = new SpannableString("天气不错");
//        span2.setSpan(new URLSpan("mailto:383209920@qq.com"),0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //邮件
        span2.setSpan(new URLSpan("https://www.baidu.com"), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //网络
//        span2.setSpan(new URLSpan("sms:17610071263"), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //短信   使用sms:或者smsto:
//        span2.setSpan(new URLSpan("mms:17610071263"), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //彩信   使用mms:或者mmsto:
//        span2.setSpan(new URLSpan("geo:38.899533,-77.036476"), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //地图
        text9.append(span2);
        text9.setMovementMethod(LinkMovementMethod.getInstance());

//        String aboutContent = "<a href=\"https://github.com/laiyuchenrushuang\" target=\"_blank\">[超链接] 天气不错</a>";
//        text9.setMovementMethod(LinkMovementMethod.getInstance());
//        text9.setText(Html.fromHtml(aboutContent));


        SpannableString stringBuilder = new SpannableString("天气不错");
        stringBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)),
                0, stringBuilder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        text10.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        text10.append(stringBuilder);

        final SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        //设置部分文字点击事件
        //设置文字
        style.append("关于本活动更多规则，请点我查看");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "触发点击事件!", Toast.LENGTH_SHORT).show();
                if (!isExpand) {
                    SpannableString spnStr = new SpannableString("KKKKKKKK");
                    text11.append(spnStr);
                    isExpand = true;
                } else {
                    SpannableString spnStr = new SpannableString("KKKKKKKK");
                    text11.setText(text11.getText().toString().replace("KKKKKKKK", ""));
                    style.setSpan(this, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text11.setMovementMethod(LinkMovementMethod.getInstance());
                    text11.append(style);
                    isExpand = false;
                }

            }
        };
        //设置字体背景色
        style.setSpan(new BackgroundColorSpan(Color.GREEN), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text11.append(style);
        //设置字体颜色
        style.setSpan(new ForegroundColorSpan(Color.GREEN), 7, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text11.append(style);
        style.setSpan(clickableSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#0000FF"));
        style.setSpan(foregroundColorSpan, 11, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        text11.setMovementMethod(LinkMovementMethod.getInstance());
        text11.append(style);


        String text12Str = text12.getText().toString();
        String strFor = text12Str.substring(0, 16);
        String strEnd = text12Str.substring(20);
        String newStr = strFor + "****" + strEnd;
        text12.setText(newStr);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int viewWidth = dm.widthPixels - Utils.dp2px(this, 20f);
        text13.initWidth(viewWidth);
        text13.setMaxLines(3);
        text13.setHasAnimation(true);
        text13.setCloseInNewLine(true);
        text13.setOpenSuffixColor(ContextCompat.getColor(this,R.color.black));
        text13.setCloseSuffixColor(ContextCompat.getColor(this,R.color.colorAccent));
        text13.setOriginalText("在全球，随着Flutter被越来越多的知名公司应用在自己的商业APP中，" +
                "Flutter这门新技术也逐渐进入了移动开发者的视野，尤其是当Google在2018年IO大会上发布了第一个" +
                "Preview版本后，国内刮起来一股学习Flutter的热潮。\n\n为了更好的方便帮助中国开发者了解这门新技术" +
                "，我们，Flutter中文网，前后发起了Flutter翻译计划、Flutter开源计划，前者主要的任务是翻译" +
                "Flutter官方文档，后者则主要是开发一些常用的包来丰富Flutter生态，帮助开发者提高开发效率。而时" +
                "至今日，这两件事取得的效果还都不错！"
        );
    }

    private void intiView() {
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);
        text10 = findViewById(R.id.text10);
        text11 = findViewById(R.id.text11);
        text12 = findViewById(R.id.text12);
        text13 = findViewById(R.id.text13);
        text14 = findViewById(R.id.text14);
    }
}