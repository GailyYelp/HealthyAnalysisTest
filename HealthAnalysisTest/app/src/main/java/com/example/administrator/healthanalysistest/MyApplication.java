package com.example.administrator.healthanalysistest;

import android.app.Application;

/**
 * Created by Administrator on 2019/4/10.
 */

public class MyApplication extends Application {
    private static MyApplication applicationAcg;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationAcg = this;
    }

    public static MyApplication getInstance() {
        return applicationAcg;
    }
}
