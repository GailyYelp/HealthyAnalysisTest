package com.example.administrator.healthanalysistest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.administrator.healthanalysistest.utils.CardItem1;
import com.example.administrator.healthanalysistest.utils.CardPagerAdapter1;
import com.example.administrator.healthanalysistest.utils.ShadowTransformer;

/**
 * Created by Administrator on 2019/4/23.
 */

public class AnxietyTestActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CardPagerAdapter1 adapter1;
    private ShadowTransformer mCardShadowTransformer;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_test);
        mViewPager=(ViewPager) findViewById(R.id.viewPager1);

        adapter1=new CardPagerAdapter1();
        adapter1.addCardItem(new CardItem1(R.color.paleturquoise,R.string.t1,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.lightgreen,R.string.t2,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.cyan,R.string.t3,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.lightcoral,R.string.t4,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.crimson,R.string.t5,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.paleturquoise,R.string.t6,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.lightgreen,R.string.t7,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.cyan,R.string.t8,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.lightcoral,R.string.t9,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));
        adapter1.addCardItem(new CardItem1(R.color.crimson,R.string.t10,R.string.trb_1,R.string.trb_1,R.string.trb_1,R.string.trb_1));


        mCardShadowTransformer=new ShadowTransformer(mViewPager,adapter1);
        mViewPager.setAdapter(adapter1);
        mViewPager.setPageTransformer(false,mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);

    }
}
