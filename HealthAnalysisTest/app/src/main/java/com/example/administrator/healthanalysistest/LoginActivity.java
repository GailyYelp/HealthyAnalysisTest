package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.healthanalysistest.utils.LogUtils;
import com.example.administrator.healthanalysistest.utils.UiTools;
import com.example.administrator.healthanalysistest.view.DialogPrompt;

import org.w3c.dom.Text;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2019/4/9.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private EditText etUserName;
    private EditText etPassword;
    private Button  btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUi();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initUi(){
        etUserName=(EditText) findViewById(R.id.et_login_username);
        etPassword=(EditText) findViewById(R.id.et_login_password);
        btnLogin=(Button)findViewById(R.id.btn_login);
        tvRegister=(TextView)findViewById(R.id.tv_registerAccount);
    }

    private void doLogin(){
        String username=etUserName.getText().toString();
        String password=etPassword.getText().toString();
        if (username.isEmpty()) {
            DialogPrompt dialogPrompt = new DialogPrompt(LoginActivity.this, R.string.please_input_user_name);
            dialogPrompt.show();
            return;
        }
        if (password.isEmpty()) {
            DialogPrompt dialogPrompt = new DialogPrompt(LoginActivity.this, R.string.please_input_password);
            dialogPrompt.show();
            return;
        }

        UiTools.showSimpleLD(this,R.string.loading_login);
        //异步执行登录
        BmobUser bmobUser=new BmobUser();
        bmobUser.setUsername(username);
        bmobUser.setPassword(password);
        bmobUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e){
                if(e==null){
                    //登录成功
                    Toast.makeText(LoginActivity.this, R.string.login_successful, Toast.LENGTH_LONG).show();
                    LoginActivity.this.setResult(RESULT_OK);
                    LoginActivity.this.finish();
                }else{
                    LogUtils.e(TAG, new Throwable(), "登录失败" + e.getErrorCode() + ":" + e.getMessage());
                    //登录失败
                    if (e.getErrorCode() == 101) {
                        DialogPrompt dialogPrompt = new DialogPrompt(LoginActivity.this, R.string.login_error_name_or_password_incorrect);
                        dialogPrompt.show();
                    } else {
                        DialogPrompt dialogPrompt = new DialogPrompt(LoginActivity.this, LoginActivity.this.getString(R.string.login_error) + "：" + e.getErrorCode() + "，" + e.getMessage());
                        dialogPrompt.show();
                    }
                }
            }
        });
    }

}
