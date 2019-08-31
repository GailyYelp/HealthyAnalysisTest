package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.gson.Body;
import com.example.administrator.healthanalysistest.gson.Result;
import com.example.administrator.healthanalysistest.gson.Results;
import com.example.administrator.healthanalysistest.gson.Transfer;
import com.example.administrator.healthanalysistest.utils.Constant;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/4/18.
 */

public class HospitalActivity extends AppCompatActivity {

    public static String uri="";
    public StringBuilder builder=new StringBuilder();
    public StringBuilder bb=new StringBuilder();

    private String province;
    private String city;
    private String area;
    private String page;

    private String currentPage;
    private String totalPage;

    EditText etProvince;
    EditText etCity;
    EditText etArea;
    EditText etPage;
    Button btnHospital;
    Button btnLook;
    TextView tvTip;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        etProvince=(EditText) findViewById(R.id.et_province);
        etCity=(EditText) findViewById(R.id.et_city);
        etArea=(EditText) findViewById(R.id.et_area);
        etPage=(EditText)findViewById(R.id.et_page);
        btnHospital=(Button)findViewById(R.id.btn_hospital_search);
        tvTip=(TextView)findViewById(R.id.tv_tip);
        btnLook=(Button)findViewById(R.id.btn_hospital_look);

        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUri();
                System.out.println(uri);
                sendRequestWithOkHttp();
                //doTip();
            }
        });

        btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HospitalActivity.this,HospitalDataActivity.class);
                intent.putExtra("data",builder.toString());
                startActivity(intent);
                //System.out.println(builder.toString());
            }
        });


    }

    private void doUri(){

        province=etProvince.getText().toString();
        city=etCity.getText().toString();
        area=etArea.getText().toString();
        page=etPage.getText().toString();
        //"https://way.jd.com/Bigmap/GeneralHospitalDataByCity?province=陕西省" +
        //"&city=西安市&district=临潼区&page=1&keyword=&appkey=079c203c9f89cb1acdb09c0e0241828b"
        uri="https://way.jd.com/Bigmap/GeneralHospitalDataByCity?province="+province+"&city="+city+
                "&district="+area+"&page="+page+"&keyword=&appkey="+ Constant.hospitalKey;
    }

    private void doTip(){
        String[] value=bb.toString().split(" ");
        System.out.println(value[0]+"---"+value[1]);
        //tvTip.setText("数据共有"+value[1]+"页，现在是第"+value[0]+"页。");
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(uri)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    System.out.println(responseData);
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {

        Gson gson = new Gson();
        //List<Body> appList = gson.fromJson(jsonData, new TypeToken<List<Body>>() {}.getType());
        Body appList=gson.fromJson(jsonData,Body.class);
        //List<Result> resultList=gson.fromJson(jsonData, new TypeToken<List<Result>>() {}.getType());
        Result resultList=appList.getResult();
        //List<Results> resultsList=gson.fromJson(jsonData, new TypeToken<List<Results>>() {}.getType());
        List<Results> resultsList=resultList.getResults();
        Log.d("MainActivity", "CurrentPage is " + resultList.getCurrentPage());
        Log.d("MainActivity", "TotalPage is " + resultList.getTotalPage());
        Log.d("MainActivity", "Total  is " + resultList.getTotal());
        bb.append(resultList.getCurrentPage());
        bb.append(" ");
        bb.append(resultList.getTotalPage());
        for (Results element:resultsList){

            Log.d("MainActivity", "name is " + element.getName());
            Log.d("MainActivity", "address is " + element.getAddress());
            Log.d("MainActivity", "type is " + element.getType());
            builder.append(element.getName()+" "+element.getAddress()+" "+element.getType()+"\n");
        }

    }
}
