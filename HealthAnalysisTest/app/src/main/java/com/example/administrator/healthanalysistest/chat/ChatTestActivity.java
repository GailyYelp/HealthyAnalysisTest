package com.example.administrator.healthanalysistest.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.healthanalysistest.R;
import com.example.administrator.healthanalysistest.bean.ChatBean;
import com.example.administrator.healthanalysistest.bean.ItemBean;
import com.example.administrator.healthanalysistest.utils.Constant;
import com.example.administrator.healthanalysistest.utils.MyAdapter;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2019/4/21.
 */

public class ChatTestActivity extends AppCompatActivity {


    List<ChatBean> ll=new ArrayList<>();
    List<ChatBean> rr=new ArrayList<>();
    RefreshLayout refreshLayout;
    private MyAdapter1 myAdapter;
    private RecyclerView recyclerView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_test);
        refreshLayout=(RefreshLayout) findViewById(R.id.refreshLayout11);
        editText=(EditText) findViewById(R.id.edit11);
        button=(Button) findViewById(R.id.buutton11);


        initData();
        setPullRefresher();


        button.setOnClickListener(new View.OnClickListener() {//点击发送消息

            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().equals("")) {
                    IMessage iMessage=new IMessage();//Bmob数据库表格类
                    iMessage.setMsg(Constant.user.getUsername()+":"+editText.getText().toString());
                    iMessage.save(new SaveListener() {
                        @Override
                        public void done(Object o, BmobException e) {
                            System.out.println("保存成功");
                            //ll.add(new ChatBean(Constant.user.getUsername(),editText.getText().toString()));
                            editText.setText("");
                            initData();
                            refreshLayout.autoRefresh();
                        }
                    });
                }
            }
        });

    }

    private void initData(){
        final FlushMessage flushMessage=new FlushMessage(this);
        flushMessage.start();
        System.out.println("进入");

        myAdapter=new MyAdapter1(Constant.constantList);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView11);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
        recyclerView.scrollToPosition(myAdapter.getItemCount()-1);
    }

    private void setPullRefresher(){

        refreshLayout.setRefreshHeader(new WaveSwipeHeader(this));
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            //执行上拉刷新的具体操作 网络请求 模拟ui

            //模拟网络请求到的数据
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                initData();
                refreshlayout.finishRefresh(1500/*,false*/);
            }
        });


    }
}
