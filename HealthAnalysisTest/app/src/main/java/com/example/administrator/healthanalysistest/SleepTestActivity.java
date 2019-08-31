package com.example.administrator.healthanalysistest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.utils.CardItem1;
import com.example.administrator.healthanalysistest.utils.CardPagerAdapter1;
import com.example.administrator.healthanalysistest.utils.ShadowTransformer;

/**
 * Created by Administrator on 2019/4/23.
 */

public class SleepTestActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CardPagerAdapter1 adapter1;
    private ShadowTransformer mCardShadowTransformer;
    private TextView tiaozhuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_test);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tiaozhuan=(TextView)findViewById(R.id.tiaozhuan);


        adapter1=new CardPagerAdapter1();
        adapter1.addCardItem(new CardItem1(R.color.paleturquoise,R.string.title_1,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.lightgreen,R.string.title_2,R.string.rb_21,R.string.rb_22,R.string.rb_23,R.string.rb_24));
        adapter1.addCardItem(new CardItem1(R.color.cyan,R.string.title_3,R.string.rb_31,R.string.rb_32,R.string.rb_33,R.string.rb_34));
        adapter1.addCardItem(new CardItem1(R.color.lightcoral,R.string.title_4,R.string.rb_41,R.string.rb_42,R.string.rb_43,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.crimson,R.string.title_5,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.paleturquoise,R.string.title_6,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.lightgreen,R.string.title_7,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.cyan,R.string.title_8,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.lightcoral,R.string.title_9,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.crimson,R.string.title_10,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.paleturquoise,R.string.title_11,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.lightgreen,R.string.title_12,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.cyan,R.string.title_13,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));
        adapter1.addCardItem(new CardItem1(R.color.lightcoral,R.string.title_14,R.string.rb_11,R.string.rb_12,R.string.rb_13,R.string.rb_14));


        mCardShadowTransformer = new ShadowTransformer(mViewPager, adapter1);
        mViewPager.setAdapter(adapter1);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);

    }
}
