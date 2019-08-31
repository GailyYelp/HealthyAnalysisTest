package com.example.administrator.healthanalysistest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.healthanalysistest.bean.User;
import com.example.administrator.healthanalysistest.utils.AppUtils;
import com.example.administrator.healthanalysistest.utils.Constant;
import com.example.administrator.healthanalysistest.utils.LogUtils;
import com.example.administrator.healthanalysistest.utils.PermissionUtils;
import com.example.administrator.healthanalysistest.utils.UiTools;
import com.example.administrator.healthanalysistest.view.DialogPromptPermission;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.Date;
import java.util.Random;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Administrator on 2019/4/10.
 */

public class MineActivity extends AppCompatActivity{

    private static final String TAG="MineActivity";

    private final int REQUEST_CODE_LOGIN = 102;
    private final int REQUEST_CODE_UPDATE = 104;
    private final int REQUEST_CODE_PERMISSIONS = 1005;
    private final int REQUEST_CODE_SELECT_FILE = 105;

    private String[] permission_login = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
    };

    private BmobFile bFile;
    ImageView rvAvatar;
    ImageView myInfo1;
    ImageView myInfo2;
    ImageView myInfo3;
    ImageView myInfo4;
    ImageView myInfo5;
    ImageView myInfo6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initUi();

        rvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                *调用系统的相册后返回的uri来选择图片，onActivityResult与之对应
                * */
                Intent choosePicIntent=new Intent(Intent.ACTION_PICK,null);
                choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(choosePicIntent,REQUEST_CODE_SELECT_FILE);


            }
        });

        myInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });

        if (PermissionUtils.checkSelfPermission(this,permission_login,REQUEST_CODE_PERMISSIONS)){
            initData();
        }
    }

    private void initUi(){
        rvAvatar=(ImageView)findViewById(R.id.rv_avatar);
        myInfo1=(ImageView)findViewById(R.id.list_1);
        myInfo2=(ImageView)findViewById(R.id.list_2);
        myInfo3=(ImageView)findViewById(R.id.list_3);
        myInfo4=(ImageView)findViewById(R.id.list_4);
        myInfo5=(ImageView)findViewById(R.id.list_5);
        myInfo6=(ImageView)findViewById(R.id.list_6);
    }


    private void updateUserData(){
        User user=new User();
        if (bFile != null) {
            Toast.makeText(MineActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
            user.setAvatar(bFile);
        }
        user.update(Constant.user.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    if (bFile != null) {
                        AppUtils.setAvatarFilePath("");
                        Toast.makeText(MineActivity.this, "设置为空", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MineActivity.this, R.string.update_complete, Toast.LENGTH_SHORT).show();
                    //MineActivity.this.setResult(RESULT_OK);
                   // MineActivity.this.finish();
                } else {
                    LogUtils.e(TAG, new Throwable(), e.getErrorCode() + "：" + e.getMessage());
                    Toast.makeText(MineActivity.this, R.string.update_error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initData(){
        File fileBase=new File(Constant.imagePath);
        if(!fileBase.exists()){
            fileBase.mkdirs();
        }
        Bmob.initialize(this,"c6187b2029bcd69fbde888e9b86882f2");
        setUserInfo();
    }

    private void setUserInfo(){
        Constant.user= User.getCurrentUser(User.class);
        if(Constant.user!=null){
            String path= AppUtils.getAvatarFilePath();
            File file =new File(path);
            if(file.exists()){
                Toast.makeText(MineActivity.this, "直接进入"+path, Toast.LENGTH_SHORT).show();
                setAvatar(path);
            }else{
                BmobFile avatarFile=Constant.user.getAvatar();
                if(avatarFile!=null){
                    avatarFile.download(new File(Constant.imagePath + File.separator + avatarFile.getFilename()), new DownloadFileListener() {
                        @Override
                        public void done(String s, BmobException e) {

                            if(e==null){
                                Toast.makeText(MineActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                                setAvatar(s);
                                AppUtils.setAvatarFilePath(s);
                                if (Looper.myLooper() == Looper.getMainLooper()) {
                                    Log.i(TAG, "主线程");
                                } else {
                                    Log.i(TAG, "子线程");
                                }
                            }

                        }

                        @Override
                        public void onProgress(Integer integer, long l) {

                        }
                    });
                }else{
                    Toast.makeText(MineActivity.this, "默认图片"+path, Toast.LENGTH_SHORT).show();
                    setDefaultAvatar();
                }
            }
        }else{
            setDefaultAvatar();
        }
    }

    private void setDefaultAvatar(){
        RequestOptions options=new RequestOptions();
        options.circleCrop();
        Glide.with(this)
                .load(R.mipmap.large)
                .apply(options)
                .into(rvAvatar);
    }

    private void setAvatar(String path){
        RequestOptions options=new RequestOptions();
        options.circleCrop();
        Glide.with(this)
                .load(path)
                .apply(options)
                .into(rvAvatar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOGIN || requestCode == REQUEST_CODE_UPDATE) {
                setUserInfo();//登录，或修改个人信息后更新数据
            }
            if(requestCode==REQUEST_CODE_SELECT_FILE){
                Uri uri=intent.getData();
                if(uri!=null){
                    File fileTemp=new File(Constant.basePath+File.separator+"temp" + new Date(System.currentTimeMillis())+
                            ".png");
                    if(fileTemp.exists()){
                        fileTemp.delete();
                    }
                    UCrop.of(uri,Uri.fromFile(fileTemp))
                            .withAspectRatio(1,1)
                            .withMaxResultSize(512,512)
                            .start(this);
                }
            }
            if(requestCode==UCrop.REQUEST_CROP){
                Log.i(TAG,"处理完成");
                Toast.makeText(MineActivity.this, "剪辑完成", Toast.LENGTH_SHORT).show();
                Uri resultUri=UCrop.getOutput(intent);
                Log.e(TAG,"resultUri.getPath()="+resultUri.getPath());
                bFile=new BmobFile(new File(resultUri.getPath()));
                setAvatar(resultUri.getPath());

                if (bFile != null) {
                    bFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {//上传文件成功
                                updateUserData();
                            } else {
                                Toast.makeText(MineActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onProgress(Integer value) {
                            super.onProgress(value);
                        }
                    });
                } else {
                    updateUserData();
                }

            }else if(resultCode==UCrop.RESULT_ERROR){
                Throwable cropError = UCrop.getError(intent);
                LogUtils.e(TAG, new Throwable(), "剪裁错误：" + cropError.getMessage());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            boolean isOK = true;
            for (int grantResult : grantResults) {
                LogUtils.i(TAG, new Throwable(), "grantResult=" + grantResult);
                if (PackageManager.PERMISSION_GRANTED != grantResult) {
                    LogUtils.i(TAG, new Throwable(), "grantResult=" + (PackageManager.PERMISSION_GRANTED != grantResult));
                    isOK = false;
                    break;
                }
            }
            if(isOK){
                initData();
            }else{
                LogUtils.e("Login",new Throwable(),"权限被拒绝");
                DialogPromptPermission dialogPromptPermission = new DialogPromptPermission(this);
                dialogPromptPermission.setPromptText("您拒绝了应用所需权限的申请，继续操作将导致部分功能无法正常使用，请在设置或安全中心开启相应的权限后重新操作");
                dialogPromptPermission.show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            MineActivity.this.setResult(RESULT_OK);
            MineActivity.this.finish();
        }
        return false;

    }

}
