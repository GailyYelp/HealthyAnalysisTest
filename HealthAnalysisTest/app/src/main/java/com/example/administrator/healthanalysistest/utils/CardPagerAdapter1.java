package com.example.administrator.healthanalysistest.utils;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.R;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter1 extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem1> mData;
    private float mBaseElevation;

    public CardPagerAdapter1() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }



    public void addCardItem(CardItem1 item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter1, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem1 item, View view) {
        CardView cardView=(CardView)view.findViewById(R.id.cardView);
        TextView titleTextView=(TextView)view.findViewById(R.id.titleTextView);
        RadioGroup rg1=(RadioGroup)view.findViewById(R.id.rg1);
        RadioButton rb1=(RadioButton)view.findViewById(R.id.rb1);
        RadioButton rb2=(RadioButton)view.findViewById(R.id.rb2);
        RadioButton rb3=(RadioButton)view.findViewById(R.id.rb3);
        RadioButton rb4=(RadioButton)view.findViewById(R.id.rb4);
        cardView.setCardBackgroundColor(view.getResources().getColor(item.getBackGroundResource()));
        titleTextView.setText(item.getmTitleResource());
        rb1.setText(item.getmButtonResource1());
        rb2.setText(item.getmButtonResource2());
        rb3.setText(item.getmButtonResource3());
        rb4.setText(item.getmButtonResource4());

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                System.out.println(radioButton.getText());
            }
        });
    }



}
