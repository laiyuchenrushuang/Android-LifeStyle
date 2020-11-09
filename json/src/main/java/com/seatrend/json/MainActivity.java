package com.seatrend.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text_json;
    TextView text_result;
    Button changeBtn;
    Button exbtn;

    String[] contentStr = {"{\n" +
            "\t\"id\":2,\"name\":\"大虾\",\n" +
            "\t\"price\":12.3,\n" +
            "\t\"imagePath\":\"http://192.168.0.1:8080/yht.jpg\"\n" +
            "}\n", "[\n" +
            "\t{\n" +
            "\t\t\"id\":1,\n" +
            "\t\t\"imagePath\":\"http://192.168.0.1:8080/test/f1.jpg\",\n" +
            "\t\t\"name\":\"小涛1\",\n" +
            "\t\t\"price\":\"12.3\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\t\"id\":2,\n" +
            "\t\t\"imagePath\":\"http://192.168.0.1:8080/test/f2.jpg\",\n" +
            "\t\t\"name\":\"小涛2\",\n" +
            "\t\t\"price\":\"12.5\"\n" +
            "\t}\n" +
            "]"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_json = findViewById(R.id.text_json);
        text_result = findViewById(R.id.text_result);
        changeBtn = findViewById(R.id.changeBtn);
        exbtn = findViewById(R.id.exbtn);

        changeBtn.setOnClickListener(this);
        exbtn.setOnClickListener(this);
        text_json.setText(contentStr[0]);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeBtn:
                if (contentStr[0].equals(text_json.getText().toString())) {
                    text_json.setText(contentStr[1]);
                } else {
                    text_json.setText(contentStr[0]);
                }
                break;

            case R.id.exbtn:

                if (contentStr[0].equals(text_json.getText().toString())) {
                    //解析json
                    try {
                        JSONObject jsonObject = new JSONObject(contentStr[0]);
                        int id = jsonObject.getInt("id");

                        String name = jsonObject.optString("name");
                        double price = jsonObject.optDouble("price");
                        String imagePath = jsonObject.optString("imagePath");

                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("解析成功，解析到的数据：").append("\n");
                        stringBuffer.append("id：").append(id).append("\n");
                        stringBuffer.append("name：").append(name).append("\n");
                        stringBuffer.append("price：").append(price).append("\n");
                        stringBuffer.append("imagePath：").append(imagePath).append("\n");
                        text_result.setText(stringBuffer.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    //解析json数据
                    try {
                        JSONArray jsonArray = new JSONArray(contentStr[1]);
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("解析成功，解析到的数据：").append("\n");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (null != jsonObject) {
                                int id = jsonObject.getInt("id");
                                String name = jsonObject.optString("name");
                                double price = jsonObject.optDouble("price");
                                String imagePath = jsonObject.optString("imagePath");
                                stringBuffer.append("数据").append(i).append(":\n");
                                stringBuffer.append("id：").append(id).append("\n");
                                stringBuffer.append("name：").append(name).append("\n");
                                stringBuffer.append("price：").append(price).append("\n");
                                stringBuffer.append("imagePath：").append(imagePath).append("\n");
                                text_result.setText(stringBuffer.toString());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}