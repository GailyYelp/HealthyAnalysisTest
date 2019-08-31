package com.example.administrator.healthanalysistest.chat;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.healthanalysistest.bean.ChatBean;
import com.example.administrator.healthanalysistest.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2019/4/23.
 */

public class FlushMessage extends Thread {
    Context context;
    int oldLen=0;
    String msg="",msg2="";
    public FlushMessage(Context context) {
        this.context = context;
    }

    @Override
    public void run() {

        BmobQuery query = new BmobQuery("IMessage");
        while (true) {

            query.findObjects(new FindListener<IMessage>() {
                @Override
                public void done(List<IMessage> arg0, BmobException e) {
                    if (e == null) {

                        if (arg0.size() != 0) {//如果服务器数据库有数据

                            if (oldLen != arg0.size()) {//如果服务器数据库的数据数跟上一次查询结果不相同
                                oldLen = arg0.size();

                                Constant.constantList.clear();
                                for(int i=0;i<arg0.size();i++){
                                    String str=arg0.get(i).getMsg();
                                    String[] values=str.split(":");

                                    Constant.constantList.add(new ChatBean(values[0],values[1]));
                                }

                                for (int i = 0; i < arg0.size(); i++) {

                                    msg = msg + arg0.get(i).getMsg() + "\n\n";//用一个String字符串储存消息
                                    ChatActivity.showTxt.setText(msg);//设置主UI界面TextView

                                }

                                msg2 = msg;//备份消息
                                msg = "";
                            } else {
                                ChatActivity.showTxt.setText(msg2);//当查询消息条目一样，主UI界面TextView设置备份消息
                            }
                        }
                    }

                }

            });
            try {
                Thread.sleep(5000);//每隔5s刷新一次数据
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }

    }
}
