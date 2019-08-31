package com.example.administrator.healthanalysistest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.healthanalysistest.bean.Survey;
import com.example.administrator.healthanalysistest.bean.User;
import com.example.administrator.healthanalysistest.utils.Constant;
import com.example.administrator.healthanalysistest.utils.LogUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2019/4/22.
 */

public class SurveyActivity extends AppCompatActivity {

    Survey user=new Survey();
    private static final String TAG="SurveyActivity";
    private  int t=0;
    private TextView tvSex;
    private TextView tvTemperature;
    private TextView tvBreath;
    private TextView tvLeftPressure;
    private TextView tvRightPressure;
    private TextView tvHeight;
    private TextView tvWeight;
    private TextView tvTestNumber;
    private TextView tvPractice;
    private TextView tvEat;
    private TextView tvSmoking;
    private TextView tvWine;
    private Button btnSubbmit1;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        initUi();
        setUserInfo();
        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("选择");
                final String[] items = {"男生", "女生", "保密", "未知"};
                adb.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        tvSex.setText(items[index]);
                    }
                });
                adb.show();
            }
        });
        tvTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("体温");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvTemperature.setText(et.getText().toString()+"℃");
                    }
                });
                adb.show();
            }
        });
        tvBreath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("呼吸频率");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvBreath.setText(et.getText().toString()+"次/分钟");
                    }
                });
                adb.show();
            }
        });
        tvLeftPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("左侧血压");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvLeftPressure.setText(et.getText().toString()+"/mmHg");
                    }
                });
                adb.show();
            }
        });
        tvRightPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("右侧血压");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvRightPressure.setText(et.getText().toString()+"/mmHg");
                    }
                });
                adb.show();
            }
        });
        tvHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("身高");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHeight.setText(et.getText().toString()+"cm");
                    }
                });
                adb.show();
            }
        });
        tvWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("体重");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvWeight.setText(et.getText().toString()+"kg");
                    }
                });
                adb.show();
            }
        });
        tvTestNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder adb=new android.app.AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("体检次数");
                final EditText et=new EditText(SurveyActivity.this);
                adb.setView(et);
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvTestNumber.setText(et.getText().toString()+"次/年");
                    }
                });
                adb.show();
            }
        });
        tvPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("体育锻炼");
                final String[] items = {"每天", "偶尔", "不锻炼"};
                adb.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        tvPractice.setText(items[index]);
                    }
                });
                adb.show();
            }
        });
        tvEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("饮食");
                final String[] items = {"荤素均衡", "荤食为主", "素食为主", "嗜盐", "嗜油", "嗜糖"};
                adb.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        tvEat.setText(items[index]);
                    }
                });
                adb.show();
            }
        });
        tvSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("吸烟");
                final String[] items = {"从不", "偶尔", "经常"};
                adb.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        tvSmoking.setText(items[index]);
                    }
                });
                adb.show();
            }
        });
        tvWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(SurveyActivity.this);
                adb.setTitle("饮酒");
                final String[] items = {"从不", "偶尔", "经常", "每天"};
                adb.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        tvWine.setText(items[index]);
                    }
                });
                adb.show();
            }
        });
        btnSubbmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData(){


        User u= BmobUser.getCurrentUser(User.class);

        String sex=tvSex.getText().toString();
        String temperature=tvTemperature.getText().toString();
        String breath=tvBreath.getText().toString();
        String pl=tvLeftPressure.getText().toString();
        String pr=tvRightPressure.getText().toString();
        String height=tvHeight.getText().toString();
        String weight=tvWeight.getText().toString();
        String number=tvTestNumber.getText().toString();
        String practice=tvPractice.getText().toString();
        String eat=tvEat.getText().toString();
        String smoking=tvSmoking.getText().toString();
        String wine=tvWine.getText().toString();

        user.setSex(sex);
        user.setTemperature(temperature);
        user.setBreath(breath);
        user.setPressureLeft(pl);
        user.setPressureRight(pr);
        user.setHeight(height);
        user.setWeight(weight);
        user.setTestNumber(number);
        user.setPractice(practice);
        user.setEat(eat);
        user.setWine(wine);
        user.setSmoking(smoking);
        user.setUser(u);


        BmobQuery<Survey> query=new BmobQuery<Survey>();
        query.addWhereEqualTo("user",u);
        query.findObjects(new FindListener<Survey>(){
            @Override
            public void done(List<Survey> list, BmobException e) {
                t=list.size();
                if(t==0){
                    user.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            Toast.makeText(SurveyActivity.this,"新建成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if(t==1){
                    user.update(list.get(0).getObjectId(),new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(SurveyActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SurveyActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }

    private void setUserInfo(){
        System.out.println("进入");
        User u= BmobUser.getCurrentUser(User.class);
        BmobQuery<Survey> query=new BmobQuery<Survey>();
        query.addWhereEqualTo("user",u);
        query.findObjects(new FindListener<Survey>(){
            @Override
            public void done(List<Survey> list, BmobException e) {
               if(list.size()>0){
                   tvSex.setText(list.get(0).getSex());
                   tvTemperature.setText(list.get(0).getTemperature());
                   tvBreath.setText(list.get(0).getBreath());
                   tvLeftPressure.setText(list.get(0).getPressureLeft());
                   tvRightPressure.setText(list.get(0).getPressureRight());
                   tvHeight.setText(list.get(0).getHeight());
                   tvWeight.setText(list.get(0).getWeight());
                   tvTestNumber.setText(list.get(0).getTestNumber());
                   tvPractice.setText(list.get(0).getPractice());
                   tvEat.setText(list.get(0).getEat());
                   tvSmoking.setText(list.get(0).getSmoking());
                   tvWine.setText(list.get(0).getWine());
               }
            }
        });
    }

    private void initUi(){
        tvSex=(TextView) findViewById(R.id.tv_sex);
        tvTemperature=(TextView) findViewById(R.id.tv_temperature);
        tvBreath=(TextView) findViewById(R.id.tv_breath);
        tvLeftPressure=(TextView) findViewById(R.id.tv_pressure_left);
        tvRightPressure=(TextView) findViewById(R.id.tv_pressure_right);
        tvHeight=(TextView) findViewById(R.id.tv_height);
        tvWeight=(TextView) findViewById(R.id.tv_weight);
        tvTestNumber=(TextView) findViewById(R.id.tv_test_number);
        tvPractice=(TextView) findViewById(R.id.tv_practice);
        tvEat=(TextView) findViewById(R.id.tv_eat);
        tvSmoking=(TextView) findViewById(R.id.tv_smoking);
        tvWine=(TextView) findViewById(R.id.tv_wine);
        btnSubbmit1=(Button) findViewById(R.id.btn_submit1);

    }
}
