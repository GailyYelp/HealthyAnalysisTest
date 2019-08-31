package com.example.administrator.healthanalysistest.utils;

import android.os.Environment;

import com.example.administrator.healthanalysistest.bean.ChatBean;
import com.example.administrator.healthanalysistest.bean.Survey;
import com.example.administrator.healthanalysistest.bean.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/10.
 */

public class Constant {
    public static String basePath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "Test";
    public static String imagePath = basePath + File.separator + "images";
    public static User user;
    public static Survey survey;
    public static String hospitalKey="70c8898016a2dbf2188b9ada2fb27c2b";
    public static List<ChatBean> constantList=new ArrayList<>();
}
