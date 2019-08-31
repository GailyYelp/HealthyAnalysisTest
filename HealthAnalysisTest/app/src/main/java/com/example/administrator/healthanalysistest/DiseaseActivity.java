package com.example.administrator.healthanalysistest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.healthanalysistest.bean.Info;
import com.example.administrator.healthanalysistest.utils.UserFoldingCellAdapter;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/22.
 */

public class DiseaseActivity extends AppCompatActivity {
    private ListView testListView;
    private UserFoldingCellAdapter adapter;
    private List<Info> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        initData();
        testListView=(ListView)findViewById(R.id.test_list_view);
        adapter=new UserFoldingCellAdapter(this,data);
        testListView.setAdapter(adapter);
        initEvent();

    }

    private void initEvent(){
        testListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((FoldingCell)view).toggle(false);
            }
        });
    }

    private void initData(){
        data=new ArrayList<>();
        data.add(new Info("Level 5","吸烟，酗酒，肥胖","心脏病",
                R.mipmap.wine,"疲劳;背部或胸部疼痛;呼吸困难;出汗;打鼾;心率不齐",
                "    心脏病是一类比较常见的循环系统疾病。循环系统由心脏、血管和调节血液循环的神经体液组织构成，循环系统疾病也称为心血管病，包括上述所有组织器官的疾病，在内科疾病中属于常见病，其中以心脏病最为多见，能显著地影响患者的劳动力。"));
        data.add(new Info("Level 4","孤独，三高，肥胖","老年痴呆",
                R.mipmap.wine,"多疑多虑;思维和判断困难;情绪不稳定;呆滞抑郁;行为异常",
                "    阿尔茨海默病（AD）是一种起病隐匿的进行性发展的神经系统退行性疾病。临床上以记忆障碍、失语、失用、失认、视空间技能损害、执行功能障碍以及人格和行为改变等全面性痴呆表现为特征，病因迄今未明。65岁以前发病者，称早老性痴呆；65岁以后发病者称老年性痴呆。"));
        data.add(new Info("Level 3","饮食不规范，熬夜","糖尿病",
                R.mipmap.wine,"多饮多尿多食和消瘦;疲乏无力;肥胖",
                "    糖尿病是一组以高血糖为特征的代谢性疾病。高血糖则是由于胰岛素分泌缺陷或其生物作用受损，或两者兼有引起。糖尿病时长期存在的高血糖，导致各种组织，特别是眼、肾、心脏、血管、神经的慢性损害、功能障碍。"));
        data.add(new Info("Level 5","脑出血，脑血栓","脑血管疾病",
                R.mipmap.wine,"眩晕;身体一侧麻木;打呵欠嗜睡;流鼻血;视觉和听力衰减",
                "    脑血管病，泛指脑部血管的各种疾病，包括脑动脉粥样硬化、血栓形成、狭窄、闭塞、脑动脉炎、脑动脉损伤、脑动脉瘤、颅内血管畸形、脑动静脉瘘等，其共同特点是引起脑组织的缺血或出血性意外，导致患者的残废或死亡，发病率占神经系统总住院病例的1/4～1/2。"));

    }
}
