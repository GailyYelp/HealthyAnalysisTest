package com.example.administrator.healthanalysistest;

import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.bean.StepData;
import com.example.administrator.healthanalysistest.utils.CommonAdapter;
import com.example.administrator.healthanalysistest.utils.CommonViewHolder;
import com.example.administrator.healthanalysistest.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/17.
 */

public class StepCounterHistoryActivity extends AppCompatActivity {
    private LinearLayout layout_titlebar;
    private ImageView iv_left;
    private ImageView iv_right;
    private ListView lv;
    private int length;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter_history);
        layout_titlebar=(LinearLayout)findViewById(R.id.layout_titlebar);
        iv_left=(ImageView)findViewById(R.id.iv_left);
        iv_right=(ImageView)findViewById(R.id.iv_right);
        lv=(ListView)findViewById(R.id.lv);

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
    }

    private void initData(){
        setEmptyView(lv);
        if (DbUtils.getLiteOrm()==null){
            DbUtils.createDb(this,"jingzhi");
        }
        List<StepData> stepDatas=DbUtils.getQueryAll(StepData.class);
        List<StepData> stepDatas30=new ArrayList<>();

        length=stepDatas.size();
        if(length>=30){
            for (int i=stepDatas.size()-1;i>=stepDatas.size()-30;i--){
                stepDatas30.add(stepDatas.get(i));
            }
            lv.setAdapter(new CommonAdapter<StepData>(this,stepDatas30,R.layout.item) {
                @Override
                protected void convertView(View item, StepData stepData) {
                    TextView tv_date= CommonViewHolder.get(item,R.id.tv_date);
                    TextView tv_step= CommonViewHolder.get(item,R.id.tv_step);
                    tv_date.setText(stepData.getToday());
                    tv_step.setText(stepData.getStep()+"步");
                }
            });
        }
        if(length<30){
            lv.setAdapter(new CommonAdapter<StepData>(this,stepDatas,R.layout.item) {
                @Override
                protected void convertView(View item, StepData stepData) {
                    TextView tv_date= CommonViewHolder.get(item,R.id.tv_date);
                    TextView tv_step= CommonViewHolder.get(item,R.id.tv_step);
                    tv_date.setText(stepData.getToday());
                    tv_step.setText(stepData.getStep()+"步");
                }
            });
        }
    }

    protected <T extends View> T setEmptyView(ListView listView) {
        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setText("暂无数据！");
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup) listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        return (T) emptyView;
    }
}
