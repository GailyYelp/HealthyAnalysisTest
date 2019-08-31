package com.example.administrator.healthanalysistest.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.R;
import com.example.administrator.healthanalysistest.utils.Constant;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2019/4/23.
 */

public class ChatActivity extends AppCompatActivity {
    static TextView showTxt;
    EditText editText;
    Button button;
    ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        //初始化控件
        showTxt=(TextView) findViewById(R.id.showtxt);
        editText=(EditText) findViewById(R.id.edit);
        button=(Button) findViewById(R.id.buutton);
        sv=(ScrollView)findViewById(R.id.scrollView);


        sv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                sv.post(new Runnable() {
                    public void run() {
                        sv.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });


        //打开一个线程实时刷新消息
        final FlushMessage flushMessage=new FlushMessage(this);
        flushMessage.start();

        button.setOnClickListener(new View.OnClickListener() {//点击发送消息

            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("")) {
                    IMessage iMessage=new IMessage();//Bmob数据库表格类
                    iMessage.setMsg(Constant.user.getUsername()+": "+editText.getText().toString());
                    iMessage.save(new SaveListener() {
                        @Override
                        public void done(Object o, BmobException e) {
                            System.out.println("保存成功");
                            //showTxt.setText(Constant.user.getUsername()+"   "+editText.getText().toString());
                            editText.setText("");
                        }
                    });
                }
            }
        });

    }

}
