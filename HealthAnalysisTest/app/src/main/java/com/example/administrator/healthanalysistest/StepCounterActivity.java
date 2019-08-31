package com.example.administrator.healthanalysistest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.healthanalysistest.service.StepService;
import com.example.administrator.healthanalysistest.utils.SharedPreferencesUtils;
import com.example.administrator.healthanalysistest.utils.UpdateUiCallBack;
import com.example.administrator.healthanalysistest.view.StepArcView;

/**
 * Created by Administrator on 2019/4/15.
 */

public class StepCounterActivity extends AppCompatActivity{
    private TextView manageStep;
    private TextView historyStep;
    private StepArcView step;
    private SharedPreferencesUtils sp;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        initData();

        historyStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StepCounterActivity.this,StepCounterHistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData(){
        manageStep=(TextView)findViewById(R.id.manage_step);
        historyStep=(TextView)findViewById(R.id.history_step);
        step=(StepArcView)findViewById(R.id.step);

        sp=new SharedPreferencesUtils(this);
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        step.setCurrentCount(Integer.parseInt(planWalk_QTY),0);
        setupService();
    }


    private boolean isBind=false;
    /*开启计步服务*/
    private void setupService(){
        Intent intent=new Intent(this, StepService.class);
        isBind=bindService(intent,conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService=((StepService.StepBinder)service).getService();
            String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
            step.setCurrentCount(Integer.parseInt(planWalk_QTY), stepService.getStepCount());

            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
                    step.setCurrentCount(Integer.parseInt(planWalk_QTY), stepCount);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) {
            this.unbindService(conn);
        }
    }
}
