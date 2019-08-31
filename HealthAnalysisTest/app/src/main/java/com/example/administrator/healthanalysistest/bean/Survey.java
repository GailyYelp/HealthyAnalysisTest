package com.example.administrator.healthanalysistest.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2019/4/22.
 */

public class Survey extends BmobObject{
    /*surveyActivity*/
    private User user;
    private String sex;
    private String temperature;
    private String breath;
    private String pressureLeft;
    private String pressureRight;
    private String height;
    private String weight;
    private String testNumber;
    private String practice;
    private String eat;
    private String smoking;
    private String wine;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBreath() {
        return breath;
    }

    public void setBreath(String breath) {
        this.breath = breath;
    }

    public String getPressureLeft() {
        return pressureLeft;
    }

    public void setPressureLeft(String pressureLeft) {
        this.pressureLeft = pressureLeft;
    }

    public String getPressureRight() {
        return pressureRight;
    }

    public void setPressureRight(String pressureRight) {
        this.pressureRight = pressureRight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getEat() {
        return eat;
    }

    public void setEat(String eat) {
        this.eat = eat;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getWine() {
        return wine;
    }

    public void setWine(String wine) {
        this.wine = wine;
    }
}
