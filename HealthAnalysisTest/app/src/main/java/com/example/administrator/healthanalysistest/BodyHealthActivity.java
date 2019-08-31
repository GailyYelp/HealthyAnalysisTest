package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Administrator on 2019/4/14.
 */

public class BodyHealthActivity extends AppCompatActivity{

    private ImageView yihuanzixun;
    private ImageView yongyaochangshi;
    private ImageView changjianjibing;
    private ImageView jiankangwenjuan;
    private ImageView buShu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_health);
        initUi();
        buShu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BodyHealthActivity.this,StepCounterActivity.class);
                startActivity(intent);
            }
        });
        yihuanzixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BodyHealthActivity.this,HospitalActivity.class);
                startActivity(intent);
            }
        });
        yongyaochangshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BodyHealthActivity.this,HealthyClassActivity.class);
                startActivity(intent);
            }
        });
        changjianjibing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BodyHealthActivity.this,DiseaseActivity.class);
                startActivity(intent);
            }
        });
        jiankangwenjuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BodyHealthActivity.this,SurveyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUi(){
        buShu=(ImageView)findViewById(R.id.bushu);
        yihuanzixun=(ImageView)findViewById(R.id.yihuanzixun);
        yongyaochangshi=(ImageView)findViewById(R.id.yongyaochangshi);
        changjianjibing=(ImageView)findViewById(R.id.changjianjibing);
        jiankangwenjuan=(ImageView)findViewById(R.id.jiankangwenjuan);
    }
}
