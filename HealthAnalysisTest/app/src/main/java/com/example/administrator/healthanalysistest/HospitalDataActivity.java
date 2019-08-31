package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2019/4/18.
 */

public class HospitalDataActivity extends AppCompatActivity {

    TextView tvData_1_name,tvData_1_address,tvData_1_type;
    TextView tvData_2_name,tvData_2_address,tvData_2_type;
    TextView tvData_3_name,tvData_3_address,tvData_3_type;
    TextView tvData_4_name,tvData_4_address,tvData_4_type;
    TextView tvData_5_name,tvData_5_address,tvData_5_type;
    TextView tvData_6_name,tvData_6_address,tvData_6_type;
    TextView tvData_7_name,tvData_7_address,tvData_7_type;
    TextView tvData_8_name,tvData_8_address,tvData_8_type;
    TextView tvData_9_name,tvData_9_address,tvData_9_type;
    TextView tvData_10_name,tvData_10_address,tvData_10_type;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_data);
        initUi();
        Intent intent=getIntent();
        String data=intent.getStringExtra("data");
        String[] values=data.split("\n");
        String[][] vv=new String[10][3];
        int i=0;
        for(String value:values){

            String elements[]=value.split(" ");
            for (String element: elements){
                System.out.println(element);
            }
            vv[i][0]=elements[0];
            vv[i][1]=elements[1];
            vv[i][2]=elements[2];
            i++;
        }
        tvData_1_name.setText(vv[0][0]);
        tvData_1_address.setText(vv[0][1]);
        tvData_1_type.setText(vv[0][2]);
        tvData_2_name.setText(vv[1][0]);
        tvData_2_address.setText(vv[1][1]);
        tvData_2_type.setText(vv[1][2]);
        tvData_3_name.setText(vv[2][0]);
        tvData_3_address.setText(vv[2][1]);
        tvData_3_type.setText(vv[2][2]);
        tvData_4_name.setText(vv[3][0]);
        tvData_4_address.setText(vv[3][1]);
        tvData_4_type.setText(vv[3][2]);
        tvData_5_name.setText(vv[4][0]);
        tvData_5_address.setText(vv[4][1]);
        tvData_5_type.setText(vv[4][2]);
        tvData_6_name.setText(vv[5][0]);
        tvData_6_address.setText(vv[5][1]);
        tvData_6_type.setText(vv[5][2]);
        tvData_7_name.setText(vv[6][0]);
        tvData_7_address.setText(vv[6][1]);
        tvData_7_type.setText(vv[6][2]);
        tvData_8_name.setText(vv[7][0]);
        tvData_8_address.setText(vv[7][1]);
        tvData_8_type.setText(vv[7][2]);
        tvData_9_name.setText(vv[8][0]);
        tvData_9_address.setText(vv[8][1]);
        tvData_9_type.setText(vv[8][2]);
        tvData_10_name.setText(vv[9][0]);
        tvData_10_address.setText(vv[9][1]);
        tvData_10_type.setText(vv[9][2]);



    }

    private void initUi(){
        tvData_1_name=(TextView)findViewById(R.id.data_1_name);
        tvData_2_name=(TextView)findViewById(R.id.data_2_name);
        tvData_3_name=(TextView)findViewById(R.id.data_3_name);
        tvData_4_name=(TextView)findViewById(R.id.data_4_name);
        tvData_5_name=(TextView)findViewById(R.id.data_5_name);
        tvData_6_name=(TextView)findViewById(R.id.data_6_name);
        tvData_7_name=(TextView)findViewById(R.id.data_7_name);
        tvData_8_name=(TextView)findViewById(R.id.data_8_name);
        tvData_9_name=(TextView)findViewById(R.id.data_9_name);
        tvData_10_name=(TextView)findViewById(R.id.data_10_name);

        tvData_1_address=(TextView)findViewById(R.id.data_1_address);
        tvData_2_address=(TextView)findViewById(R.id.data_2_address);
        tvData_3_address=(TextView)findViewById(R.id.data_3_address);
        tvData_4_address=(TextView)findViewById(R.id.data_4_address);
        tvData_5_address=(TextView)findViewById(R.id.data_5_address);
        tvData_6_address=(TextView)findViewById(R.id.data_6_address);
        tvData_7_address=(TextView)findViewById(R.id.data_7_address);
        tvData_8_address=(TextView)findViewById(R.id.data_8_address);
        tvData_9_address=(TextView)findViewById(R.id.data_9_address);
        tvData_10_address=(TextView)findViewById(R.id.data_10_address);

        tvData_1_type=(TextView)findViewById(R.id.data_1_type);
        tvData_2_type=(TextView)findViewById(R.id.data_2_type);
        tvData_3_type=(TextView)findViewById(R.id.data_3_type);
        tvData_4_type=(TextView)findViewById(R.id.data_4_type);
        tvData_5_type=(TextView)findViewById(R.id.data_5_type);
        tvData_6_type=(TextView)findViewById(R.id.data_6_type);
        tvData_7_type=(TextView)findViewById(R.id.data_7_type);
        tvData_8_type=(TextView)findViewById(R.id.data_8_type);
        tvData_9_type=(TextView)findViewById(R.id.data_9_type);
        tvData_10_type=(TextView)findViewById(R.id.data_10_type);

    }
}
