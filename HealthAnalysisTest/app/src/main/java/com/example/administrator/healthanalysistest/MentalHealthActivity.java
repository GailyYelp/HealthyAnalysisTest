package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.chat.ChatActivity;
import com.example.administrator.healthanalysistest.chat.ChatTestActivity;
import com.example.administrator.healthanalysistest.chat.FlushMessage;

/**
 * Created by Administrator on 2019/4/23.
 */

public class MentalHealthActivity extends AppCompatActivity {

    TextView tvTestAnxiety;
    TextView tvTestSleep;
    TextView tvDepression;
    ImageView ivChat;
    ImageView xinlizixong;
    ImageView healthStandard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_healty);

        initUi();

        tvTestSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentalHealthActivity.this,SleepTestActivity.class);
                startActivity(intent);
            }
        });

        tvTestAnxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentalHealthActivity.this,AnxietyTestActivity.class);
                startActivity(intent);
            }
        });

        tvDepression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentalHealthActivity.this,DepressionTestActivity.class);
                startActivity(intent);
            }
        });

        ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentalHealthActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        xinlizixong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentalHealthActivity.this, CounselingActivity.class);
                startActivity(intent);
            }
        });

        healthStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MentalHealthActivity.this, LineCharActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initUi(){
        tvTestAnxiety=(TextView) findViewById(R.id.test_mental);
        tvTestSleep=(TextView) findViewById(R.id.test_sleep);
        tvDepression=(TextView)findViewById(R.id.test_depression);
        ivChat=(ImageView)findViewById(R.id.chat);
        xinlizixong=(ImageView)findViewById(R.id.xinlizixun);
        healthStandard=(ImageView)findViewById(R.id.health_standard);
    }
}
