package com.example.administrator.healthanalysistest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.healthanalysistest.bean.User;
import com.example.administrator.healthanalysistest.utils.Constant;
import com.example.administrator.healthanalysistest.utils.LogUtils;
import com.example.administrator.healthanalysistest.utils.PermissionUtils;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2019/4/11.
 */

public class UserInfoActivity extends FragmentActivity
        implements DatePickerDialog.OnDateSetListener{

    private static final String TAG="UserInfoActivity";
    public static final String DATEPICKER_TAG = "datepicker";
    TextView datePicker;
    TextView realName;
    TextView contactNumber;
    Button submit;

    private String[] permission_login = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_user_info);
        initUi();
        setUserInfo();
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), true);

        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(true);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }
        }

        realName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb=new AlertDialog.Builder(UserInfoActivity.this);
                adb.setTitle("真实姓名");
                final EditText et=new EditText(UserInfoActivity.this);
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
                        realName.setText(et.getText().toString());
                    }
                });
                adb.show();
            }
        });

        contactNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb=new AlertDialog.Builder(UserInfoActivity.this);
                adb.setTitle("联系人方式");
                final EditText et=new EditText(UserInfoActivity.this);
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
                        contactNumber.setText(et.getText().toString());
                    }
                });
                adb.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });


    }

    private void initUi(){
        datePicker=(TextView) findViewById(R.id.tv_date_picker);
        realName=(TextView)findViewById(R.id.tv_real_name);
        contactNumber=(TextView)findViewById(R.id.tv_contact_number);
        submit=(Button)findViewById(R.id.btn_submit);
    }

    private void initData(){
        User user=new User();
        String birth_Date=datePicker.getText().toString();
        String real_Name=realName.getText().toString();
        String contact_number=contactNumber.getText().toString();

        user.setBirthDate(birth_Date);
        user.setRealName(real_Name);
        user.setChildNumber(contact_number);

        user.update(Constant.user.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(UserInfoActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }else {
                    LogUtils.e(TAG, new Throwable(), e.getErrorCode() + "：" + e.getMessage());
                    Toast.makeText(UserInfoActivity.this, R.string.update_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUserInfo(){
        datePicker.setText(Constant.user.getBirthDate());
        realName.setText(Constant.user.getRealName());
        contactNumber.setText(Constant.user.getChildNumber());

    }
    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        datePicker.setText(year+"-"+month+"-"+day);
    }



}
