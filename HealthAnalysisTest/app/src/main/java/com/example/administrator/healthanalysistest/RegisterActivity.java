package com.example.administrator.healthanalysistest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.healthanalysistest.bean.User;
import com.example.administrator.healthanalysistest.utils.LogUtils;
import com.example.administrator.healthanalysistest.view.DialogPrompt;

import java.util.Date;

import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2019/4/10.
 */

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    EditText etRegistUsername;
    EditText etRegistPassword;
    EditText etRegistConfirmPasword;
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUi();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });

    }

    private void initUi(){
        etRegistUsername=(EditText) findViewById(R.id.et_register_username);
        etRegistPassword=(EditText)findViewById(R.id.et_register_password);
        etRegistConfirmPasword=(EditText)findViewById(R.id.et_register_confirmPassword);
        btnRegister=(Button)findViewById(R.id.btn_register);
    }

    private void doRegister(){
        String name=etRegistUsername.getText().toString();
        String pwd=etRegistPassword.getText().toString();
        String pwdConfirm=etRegistConfirmPasword.getText().toString();
        if(name.isEmpty()){
            DialogPrompt dialogPrompt=new DialogPrompt(RegisterActivity.this,R.string.user_name_is_not_empty);
            dialogPrompt.show();
            return;
        }
        if (pwd.isEmpty()) {
            DialogPrompt dialogPrompt = new DialogPrompt(RegisterActivity.this, R.string.please_input_password);
            dialogPrompt.show();
            return;
        }
        if (!pwd.equals(pwdConfirm)) {
            DialogPrompt dialogPrompt = new DialogPrompt(RegisterActivity.this, R.string.password_new_different);
            dialogPrompt.show();
            return;
        }

        User user = new User();
        user.setUsername(name);
        user.setPassword(pwd);
        user.setLevel(1);
        user.setSex("保密");
        user.setSignature(getString(R.string.signature));
        user.setAbo("2B");
        user.setBirthday(new BmobDate(new Date()));
        user.setPronoun("一级萌新");
        user.setConstellation("天秤座");
        user.setLevelScore(1);

        user.signUp(new SaveListener<User>() {
           @Override
            public void done(User user, BmobException e){
               if(e==null){
                   DialogPrompt dialogPrompt = new DialogPrompt(RegisterActivity.this, R.string.register_ok, 3);
                   dialogPrompt.showAndFinish(RegisterActivity.this);
               }else{
                   LogUtils.i(TAG, new Throwable(), e.getErrorCode() + ":" + e.getMessage());
                   if (e.getErrorCode() == 202) {
                       DialogPrompt dialogPrompt = new DialogPrompt(RegisterActivity.this, R.string.register_error_user_already_taken);
                       dialogPrompt.show();
                   } else {
                       DialogPrompt dialogPrompt = new DialogPrompt(RegisterActivity.this, getString(R.string.register_error) + "，错误码：" + e.getErrorCode() + "," + e.getMessage());
                       dialogPrompt.show();
                   }
               }
           }
        });
    }
}
