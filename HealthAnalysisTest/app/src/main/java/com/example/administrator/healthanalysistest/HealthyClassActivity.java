package com.example.administrator.healthanalysistest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.healthanalysistest.bean.ItemBean;
import com.example.administrator.healthanalysistest.utils.MyAdapter;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/21.
 */

public class HealthyClassActivity extends AppCompatActivity {

    RefreshLayout refreshLayout;
    private List<ItemBean> list;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_class);
        refreshLayout=(RefreshLayout) findViewById(R.id.refreshLayout);

        initData();
        setPullRefresher();

    }

    private void initData(){
        list=new ArrayList<ItemBean>();
        list.add(new ItemBean(R.mipmap.icons8_1,R.string.changshi_1));
        list.add(new ItemBean(R.mipmap.icons8_2,R.string.changshi_2));
        list.add(new ItemBean(R.mipmap.icons8_3,R.string.changshi_3));
        list.add(new ItemBean(R.mipmap.icons8_4,R.string.changshi_4));
        list.add(new ItemBean(R.mipmap.icons8_5,R.string.changshi_5));
        list.add(new ItemBean(R.mipmap.icons8_6,R.string.changshi_6));
        list.add(new ItemBean(R.mipmap.icons8_7,R.string.changshi_7));
        list.add(new ItemBean(R.mipmap.icons8_8,R.string.changshi_8));
        list.add(new ItemBean(R.mipmap.icons8_9,R.string.changshi_9));
        list.add(new ItemBean(R.mipmap.icons8_10,R.string.changshi_10));



        myAdapter=new MyAdapter(list);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private void setPullRefresher(){

        refreshLayout.setRefreshHeader(new WaveSwipeHeader(this));
        refreshLayout.setRefreshFooter(new BallPulseFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            //执行上拉刷新的具体操作 网络请求 模拟ui

            //模拟网络请求到的数据
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                ArrayList<ItemBean> newList = new ArrayList<ItemBean>();
                newList.add(new ItemBean(R.mipmap.icons8_1,R.string.xinchangshi_1));
                newList.add(new ItemBean(R.mipmap.icons8_2,R.string.xinchangshi_2));
                newList.add(new ItemBean(R.mipmap.icons8_3,R.string.xinchangshi_3));
                newList.add(new ItemBean(R.mipmap.icons8_4,R.string.xinchangshi_4));
                newList.add(new ItemBean(R.mipmap.icons8_5,R.string.xinchangshi_5));
                newList.add(new ItemBean(R.mipmap.icons8_6,R.string.xinchangshi_6));
                newList.add(new ItemBean(R.mipmap.icons8_7,R.string.xinchangshi_7));
                newList.add(new ItemBean(R.mipmap.icons8_8,R.string.xinchangshi_8));
                newList.add(new ItemBean(R.mipmap.icons8_9,R.string.xinchangshi_9));
                newList.add(new ItemBean(R.mipmap.icons8_10,R.string.xinchangshi_10));
                myAdapter.refresh(newList);
                refreshlayout.finishRefresh(1500/*,false*/);
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ArrayList<ItemBean> newList = new ArrayList<ItemBean>();
                newList.add(new ItemBean(R.mipmap.icons8_11,R.string.changshi_111));
                newList.add(new ItemBean(R.mipmap.icons8_12,R.string.changshi_12));
                newList.add(new ItemBean(R.mipmap.icons8_13,R.string.changshi_13));
                newList.add(new ItemBean(R.mipmap.icons8_14,R.string.changshi_14));
                newList.add(new ItemBean(R.mipmap.icons8_15,R.string.changshi_15));
                newList.add(new ItemBean(R.mipmap.icons8_16,R.string.changshi_16));
                newList.add(new ItemBean(R.mipmap.icons8_17,R.string.changshi_17));
                newList.add(new ItemBean(R.mipmap.icons8_18,R.string.changshi_18));
                newList.add(new ItemBean(R.mipmap.icons8_19,R.string.changshi_19));
                newList.add(new ItemBean(R.mipmap.icons8_20,R.string.changshi_20));

                myAdapter.add(newList);
                refreshlayout.finishLoadmore(1500);

            }
        });
    }
}
