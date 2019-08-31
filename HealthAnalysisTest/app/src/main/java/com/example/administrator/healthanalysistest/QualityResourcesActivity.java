package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.longsh.optionframelibrary.OptionBottomDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/6.
 */

public class QualityResourcesActivity extends AppCompatActivity{
    ImageView medicalResources;
    ImageView foodResources;
    ImageView drugStoreResources;
    ImageView communityResources;
    ImageView trafficResources;
    ImageView otherResources;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_resources);
        initUi();

        medicalResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QualityResourcesActivity.this,HospitalActivity.class);
                startActivity(intent);
            }
        });

        foodResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> stringList = new ArrayList<String>();
                stringList.add("袁记肉夹馍");
                stringList.add("魏家凉皮");
                stringList.add("老米家泡馍");
                stringList.add("沙县小吃");
                stringList.add("肯德基");
                stringList.add("麦当劳");

                final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(QualityResourcesActivity.this, stringList);
                optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       optionBottomDialog.dismiss();
                    }
                });

            }
        });

        drugStoreResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> stringList = new ArrayList<String>();
                stringList.add("XXX大药房");
                stringList.add("XXX大药店");
                stringList.add("XXX中药馆");
                stringList.add("XXX诊所");


                final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(QualityResourcesActivity.this, stringList);
                optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        optionBottomDialog.dismiss();
                    }
                });
            }
        });

        communityResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> stringList = new ArrayList<String>();
                stringList.add("预约舞蹈");
                stringList.add("预约桥牌");
                stringList.add("预约象棋");
                stringList.add("预约广场舞");


                final OptionBottomDialog optionBottomDialog = new OptionBottomDialog(QualityResourcesActivity.this, stringList);
                optionBottomDialog.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        optionBottomDialog.dismiss();

                    }
                });
            }
        });

    }

    private void initUi(){
        medicalResources=(ImageView)findViewById(R.id.medical_resources);
        foodResources=(ImageView)findViewById(R.id.food_resources);
        drugStoreResources=(ImageView)findViewById(R.id.drugstore_resources);
        communityResources=(ImageView)findViewById(R.id.community_resources);
        trafficResources=(ImageView)findViewById(R.id.traffic_resources);
        otherResources=(ImageView)findViewById(R.id.other_resources);

    }
}
