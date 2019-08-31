package com.example.administrator.healthanalysistest.chat;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2019/4/23.
 */

public class IMessage extends BmobObject {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
