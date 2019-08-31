package com.example.administrator.healthanalysistest.bean;

/**
 * Created by Administrator on 2019/4/24.
 */

public class ChatBean {
    private String chaterName;
    private String chaterMessage;

    public ChatBean(String chaterName, String chaterMessage) {
        this.chaterName = chaterName;
        this.chaterMessage = chaterMessage;
    }

    public String getChaterName() {
        return chaterName;
    }

    public void setChaterName(String chaterName) {
        this.chaterName = chaterName;
    }

    public String getChaterMessage() {
        return chaterMessage;
    }

    public void setChaterMessage(String chaterMessage) {
        this.chaterMessage = chaterMessage;
    }
}
