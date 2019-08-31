package com.example.administrator.healthanalysistest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.healthanalysistest.bean.User;
import com.example.administrator.healthanalysistest.fragment.OneFragment;
import com.example.administrator.healthanalysistest.fragment.TabFragmentPagerAdapter;
import com.example.administrator.healthanalysistest.fragment.ThreeFragment;
import com.example.administrator.healthanalysistest.fragment.TwoFragment;
import com.example.administrator.healthanalysistest.utils.AppUtils;
import com.example.administrator.healthanalysistest.utils.Constant;
import com.example.administrator.healthanalysistest.utils.LogUtils;
import com.example.administrator.healthanalysistest.utils.PermissionUtils;
import com.example.administrator.healthanalysistest.view.DialogPromptPermission;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnBannerListener{

    private static final String TAG = "MainActivity";

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView  avatarMin;
    private ImageView avatarMid;
    private TextView tvUsername,tvSignature;
    private long lastClickTime;

    private Banner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;

    private TextView tv_item_one;
    private TextView tv_item_two;
    private TextView tv_item_three;
    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    private ImageView sos;
    private ImageView call;

    private final int REQUEST_CODE_LOGIN = 102;
    private final int REQUEST_CODE_UPDATE = 104;
    private final int REQUEST_CODE_PERMISSIONS = 1005;

    private String[] permission_login = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        avatarMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.user == null) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_LOGIN);
                } else{
                    if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        drawerLayout.closeDrawers();
                    } else {
                        drawerLayout.openDrawer(Gravity.LEFT);
                    }
                }
            }
        });

        avatarMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MineActivity.class);
                startActivityForResult(intent, REQUEST_CODE_LOGIN);
            }
        });

        if (PermissionUtils.checkSelfPermission(this, permission_login, REQUEST_CODE_PERMISSIONS)) {
            initData();
        }

        myViewPager.addOnPageChangeListener(new MyPagerChangeListener());
        list=new ArrayList<>();
        list.add(new OneFragment());
        list.add(new ThreeFragment());
        list.add(new TwoFragment());

        adapter =new TabFragmentPagerAdapter(getSupportFragmentManager(),list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);  //初始化显示第一个页面

        tv_item_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(0);
            }
        });
        tv_item_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(1);
            }
        });
        tv_item_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(2);
            }
        });

        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SosActivity.class);
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);

            }
        });

    }

    private void initData(){
        File fileBase = new File(Constant.imagePath);
        if (!fileBase.exists()) {
            fileBase.mkdirs();
        }
        Bmob.initialize(this,"c6187b2029bcd69fbde888e9b86882f2");
        setUserInfo();
    }

    private void setUserInfo(){
        Constant.user = User.getCurrentUser(User.class);//取用缓存的数据
        if (Constant.user != null) {
            tvUsername.setText(Constant.user.getUsername());
            String path = AppUtils.getAvatarFilePath();
            File file = new File(path);
            if (file.exists()) {
                setAvatar(path);
            } else {
                //头像文件不存在，有可能是图片被删除了，或者没有设置头像
                BmobFile avatarFile = Constant.user.getAvatar();
                if (avatarFile != null) {//如果是有头像的就下载下来,用于缓存头像
                    avatarFile.download(new File(Constant.imagePath + File.separator + avatarFile.getFilename()), new DownloadFileListener() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                //下载成功
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
                } else {
                    setDefaultAvatar();
                }
            }
            if (Constant.user.getSignature() != null && !Constant.user.getSignature().equals("")) {
                tvSignature.setText(Constant.user.getSignature());
            }
        } else {
            //使用Glide裁剪出圆形图片，Glide是个不错的图片加载库，感觉挺好用的，推荐。
            setDefaultAvatar();
            tvUsername.setText(R.string.click_user_image);
            tvSignature.setText(R.string.signature);
        }
    }

    private void setDefaultAvatar() {
        RequestOptions options = new RequestOptions();
        options.circleCrop();
        Glide.with(this)
                .load(R.mipmap.large)
                .apply(options)
                .into(avatarMin);
        Glide.with(this)
                .load(R.mipmap.large)
                .apply(options)
                .into(avatarMid);
    }

    private void setAvatar(String path) {
        RequestOptions options = new RequestOptions();
        options.circleCrop();
        Glide.with(this)
                .load(path)
                .apply(options)
                .into(avatarMin);
        Glide.with(this)
                .load(path)
                .apply(options)
                .into(avatarMid);
    }

    private void initUi(){

        avatarMin=findViewById(R.id.avatar_min);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        avatarMid=(ImageView) navigationView.getHeaderView(0).findViewById(R.id.avatar_mid);
        tvUsername=(TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_userName);
        tvSignature=(TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_signature);
        navigationView.setNavigationItemSelectedListener(this);
        banner=(Banner)findViewById(R.id.banner);
        list_path=new ArrayList<>();
        list_title=new ArrayList<>();
        list_path.add("https://wx1.sinaimg.cn/mw690/9b0571ebly1g2di52kvsqj218g0rkkjl.jpg");
        list_path.add("http://img.mp.itc.cn/upload/20160516/4b474094b29746d2a5465b2704df6a0c.jpg");
        list_path.add("http://n.sinaimg.cn/sinacn20190524s/418/w780h438/20190524/ac42-hxntqyy2642630.jpg");
        list_path.add("http://n.sinaimg.cn/translate/20170121/RyeU-fxzusxt7962602.jpg");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR );
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.BackgroundToForeground);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


        tv_item_one = (TextView) findViewById(R.id.tv_item_one);
        tv_item_two = (TextView) findViewById(R.id.tv_item_two);
        tv_item_three = (TextView) findViewById(R.id.tv_item_three);
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);

        sos=findViewById(R.id.sos);
        call=findViewById(R.id.call);

    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (lastClickTime == 0) {
                lastClickTime = System.currentTimeMillis();
                Toast.makeText(this, R.string.close_click_again, Toast.LENGTH_SHORT).show();
            } else {
                long now = System.currentTimeMillis();
                if (now - lastClickTime < 3000) {
                    super.onBackPressed();
                } else {
                    lastClickTime = now;
                    Toast.makeText(this, R.string.close_click_again, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOGIN || requestCode == REQUEST_CODE_UPDATE) {
                setUserInfo();//登录，或修改个人信息后更新数据
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
            if (isOK) {
                initData();
            } else {
                // 用户授权拒绝之后，友情提示一下就可以了
                LogUtils.e("Login", new Throwable(), "权限被拒绝");
                // 这里应该弹出dialog详细说明一下
                // Toast.makeText(this,
                // "您拒绝了所需录音权限的申请，将不能进行操作，请在设置或安全中心开启该项权限后重新操作",
                // Toast.LENGTH_LONG).show();
                DialogPromptPermission dialogPromptPermission = new DialogPromptPermission(this);
                dialogPromptPermission.setPromptText("您拒绝了应用所需权限的申请，继续操作将导致部分功能无法正常使用，请在设置或安全中心开启相应的权限后重新操作");
                dialogPromptPermission.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_health) {
            Intent intent=new Intent(MainActivity.this,BodyHealthActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_mental) {
            Intent intent=new Intent(MainActivity.this,MentalHealthActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_resource) {
            Intent intent=new Intent(MainActivity.this,QualityResourcesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_safe) {
            Intent intent=new Intent(MainActivity.this,LocationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            logout();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        BmobUser.logOut();   //清除缓存用户对象
        AppUtils.setAvatarFilePath("");
        Constant.user = null;
        tvSignature.setText(R.string.signature);
        tvUsername.setText(R.string.click_user_image);
        setDefaultAvatar();
    }


    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
}
class MyLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        RoundedCorners roundedCorners= new RoundedCorners(20);
    //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(290, 150);

        Glide.with(context).load((String) path).apply(options).into(imageView);
    }
}
