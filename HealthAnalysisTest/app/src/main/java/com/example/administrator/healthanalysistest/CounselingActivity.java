package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.spark.submitbutton.SubmitButton;

/**
 * Created by Administrator on 2019/4/28.
 */

public class CounselingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counseling);
        SubmitButton sb = (SubmitButton) findViewById(R.id.btn_cool);
        final Button btnIntelligent=(Button)findViewById(R.id.btn_intelligent);
        final Button btnDoctor=(Button)findViewById(R.id.btn_doctor);
        btnIntelligent.setVisibility(View.GONE);
        btnDoctor.setVisibility(View.GONE);

        sb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnDoctor.setVisibility(View.VISIBLE);
                btnIntelligent.setVisibility(View.VISIBLE);
            }
        });

        btnIntelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CounselingActivity.this,IntelligentAdvisoryActivity.class);
                startActivity(intent);
            }
        });

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CounselingActivity.this,HospitalActivity.class);
                startActivity(intent);
            }
        });
    }
}
