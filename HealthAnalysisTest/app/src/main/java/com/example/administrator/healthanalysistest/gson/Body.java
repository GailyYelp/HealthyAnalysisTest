package com.example.administrator.healthanalysistest.gson;

/**
 * Created by Administrator on 2019/4/17.
 */

public class Body {
    private String code;
    private String charge;
    private String remain;
    private String msg;
    Result result;
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }






}
