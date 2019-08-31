package com.example.administrator.healthanalysistest;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.mikephil.charting.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */

public class LineCharActivity extends AppCompatActivity
        implements OnChartGestureListener,OnChartValueSelectedListener, View.OnClickListener{

    private LineChart mLineChar;
    private LineDataSet set1;
    private BarChart mBarChart;
    private LineChart mDoubleLineChar;
    private PieChart mPieChart;
    private PieChart mPieChart1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_char);

        initView();
        initView1();
        initView2();
        initView3();
        initView4();
    }

    //初始化View
    private void initView() {

        mLineChar = (LineChart) findViewById(R.id.mLineChar);
        //设置手势滑动事件
        mLineChar.setOnChartGestureListener(this);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(this);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        //设置缩放
        mLineChar.setDragEnabled(true);
        //设置推动
        mLineChar.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);



        LimitLine llXAxis = new LimitLine(10f, "标记");
        //设置线宽
        llXAxis.setLineWidth(4f);
        //
        llXAxis.enableDashedLine(10f, 10f, 0f);
        //设置
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        //x轴
        XAxis xAxis = mLineChar.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置x轴的最大值
        xAxis.setAxisMaximum(24f);
        //设置x轴的最小值
        xAxis.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return  (int)value+"/时";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        LimitLine ll1 = new LimitLine(37.0f, "正常上限");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(36.0f, "正常下限");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);


        YAxis leftAxis = mLineChar.getAxisLeft();
        //重置所有限制线,以避免重叠线
        leftAxis.removeAllLimitLines();
        //设置优秀线
        leftAxis.addLimitLine(ll1);
        //设置及格线
        leftAxis.addLimitLine(ll2);
        //y轴最大
        leftAxis.setAxisMaximum(38.5f);
        //y轴最小
        leftAxis.setAxisMinimum(35.5f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value+"℃";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        leftAxis.setDrawZeroLine(true);

        // 限制数据(而不是背后的线条勾勒出了上面)
        leftAxis.setDrawLimitLinesBehindData(true);

        mLineChar.getAxisRight().setEnabled(false);

        //这里我模拟一些数据
        ArrayList<Entry> values = new ArrayList<>();

        values.add(new Entry(0, 36.2f));
        values.add(new Entry(2, 36.0f));
        values.add(new Entry(4, 36.2f));
        values.add(new Entry(6, 36.3f));
        values.add(new Entry(8, 36.4f));
        values.add(new Entry(10, 36.5f));
        values.add(new Entry(12, 36.6f));
        values.add(new Entry(14, 36.7f));
        values.add(new Entry(16, 36.9f));
        values.add(new Entry(18, 37.0f));
        values.add(new Entry(20, 36.8f));
        values.add(new Entry(22, 36.5f));
        values.add(new Entry(24, 36.1f));

        /*
        values.add(new Entry(5, 50));
        values.add(new Entry(10, 66));
        values.add(new Entry(15, 120));
        values.add(new Entry(20, 30));
        values.add(new Entry(35, 10));
        values.add(new Entry(40, 110));
        values.add(new Entry(45, 30));
        values.add(new Entry(50, 160));
        values.add(new Entry(100, 30));
        */

        //设置数据
        setData(values);

        //默认动画
        mLineChar.animateXY(2500,2500);
        //刷新
        //mChart.invalidate();

        // 得到这个文字
        Legend l = mLineChar.getLegend();

        // 修改文字 ...
        l.setForm(Legend.LegendForm.CIRCLE);


    }

    //传递数据集
    private void setData(ArrayList<Entry> values) {
        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型

            set1 = new LineDataSet(values, "体温反馈");

            // 在这里设置线
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            //set1.enableDashedLine(10f, 5f, 0f);
            //set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setHighLightColor(R.color.yellow);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(true);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(R.color.skyblue);
            } else {
                set1.setFillColor(Color.BLACK);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(set1);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);

            //谁知数据
            mLineChar.setData(data);
        }
    }

    private void initView1(){
        mBarChart = (BarChart) findViewById(R.id.mBarChart);
        mBarChart.setOnChartValueSelectedListener(this);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        // 如果60多个条目显示在图表,drawn没有值
        mBarChart.setMaxVisibleValueCount(60);
        // 扩展现在只能分别在x轴和y轴
        mBarChart.setPinchZoom(false);
        //是否显示表格颜色
        mBarChart.setDrawGridBackground(false);



        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        // 只有1天的时间间隔
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setAxisMaximum(24f);
        xAxis.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value+"时";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });



        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(60f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value+"次/分";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });
        mBarChart.getAxisRight().setEnabled(false);

        // 设置标示，就是那个一组y的value的
        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        //样式
        l.setForm(Legend.LegendForm.SQUARE);
        //字体
        l.setFormSize(9f);
        //大小
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        //模拟数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(0, 65));
        yVals1.add(new BarEntry(2, 70));
        yVals1.add(new BarEntry(4, 78));
        yVals1.add(new BarEntry(6, 80));
        yVals1.add(new BarEntry(8, 74));
        yVals1.add(new BarEntry(10, 75));
        yVals1.add(new BarEntry(12, 75));
        yVals1.add(new BarEntry(14, 75));
        yVals1.add(new BarEntry(16, 80));
        yVals1.add(new BarEntry(18, 78));
        yVals1.add(new BarEntry(20, 90));
        yVals1.add(new BarEntry(22, 75));
        yVals1.add(new BarEntry(24, 70));
        setData1(yVals1);

    }

    private void setData1(ArrayList yVals1) {
        float start = 1f;
        BarDataSet set1;
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "心率反馈");
            //设置有四种颜色
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);

            data.setBarWidth(1.8f);
            //设置数据
            mBarChart.setData(data);
            mBarChart.setFitBars(true);
            mBarChart.animateXY(2500,2500);
        }
    }

    private void initView2(){
        mDoubleLineChar = (LineChart) findViewById(R.id.mDoubleLineChar);
        mDoubleLineChar.setOnChartValueSelectedListener(this);
        // 没有描述的文本
        mDoubleLineChar.getDescription().setEnabled(false);
        // 支持触控手势
        mDoubleLineChar.setTouchEnabled(true);
        mDoubleLineChar.setDragDecelerationFrictionCoef(0.9f);
        // 支持缩放和拖动
        mDoubleLineChar.setDragEnabled(true);
        mDoubleLineChar.setScaleEnabled(true);
        mDoubleLineChar.setDrawGridBackground(false);
        mDoubleLineChar.setHighlightPerDragEnabled(true);
        // 如果禁用,扩展可以在x轴和y轴分别完成
        mDoubleLineChar.setPinchZoom(true);
        // 设置背景颜色(灰色)
        mDoubleLineChar.setBackgroundColor(Color.WHITE);
        //设置数据
        setData2();
        //默认x动画
        mDoubleLineChar.animateXY(2500,2500);

        //获得数据
        Legend l = mDoubleLineChar.getLegend();

        //修改
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        //x轴
        XAxis xAxis = mDoubleLineChar.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMaximum(24F);
        xAxis.setAxisMinimum(0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value+"mmHg";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        //左边y轴
        YAxis leftAxis = mDoubleLineChar.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMaximum(140f);
        leftAxis.setAxisMinimum(65f);
        leftAxis.setDrawAxisLine(true);
        //leftAxis.setGranularityEnabled(true);
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value+"mmHg";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        mDoubleLineChar.getAxisRight().setEnabled(false);

    }

    private void setData2() {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        yVals1.add(new Entry(0, 70f));
        yVals1.add(new Entry(2, 75f));
        yVals1.add(new Entry(4, 80f));
        yVals1.add(new Entry(6, 85f));
        yVals1.add(new Entry(8, 75f));
        yVals1.add(new Entry(10, 80f));
        yVals1.add(new Entry(12, 85f));
        yVals1.add(new Entry(14, 90f));
        yVals1.add(new Entry(16, 95f));
        yVals1.add(new Entry(18, 85f));
        yVals1.add(new Entry(20, 86f));
        yVals1.add(new Entry(22, 87f));
        yVals1.add(new Entry(24, 100f));


        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        yVals2.add(new Entry(0, 80f));
        yVals2.add(new Entry(2, 86f));
        yVals2.add(new Entry(4, 84f));
        yVals2.add(new Entry(6, 92f));
        yVals2.add(new Entry(8, 102f));
        yVals2.add(new Entry(10, 115f));
        yVals2.add(new Entry(12, 120f));
        yVals2.add(new Entry(14, 110f));
        yVals2.add(new Entry(16, 99f));
        yVals2.add(new Entry(18, 103f));
        yVals2.add(new Entry(20, 98f));
        yVals2.add(new Entry(22, 91f));
        yVals2.add(new Entry(24, 90f));

        LineDataSet set1, set2;

        if (mDoubleLineChar.getData() != null &&
                mDoubleLineChar.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mDoubleLineChar.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mDoubleLineChar.getData().getDataSetByIndex(1);

            set1.setValues(yVals1);
            set2.setValues(yVals2);
            mDoubleLineChar.getData().notifyDataChanged();
            mDoubleLineChar.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(yVals1, "收缩血压");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.isDrawValuesEnabled();
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.GREEN);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);

            //创建一个数据集,并给它一个类型
            set2 = new LineDataSet(yVals2, "舒张血压");
            set1.isDrawValuesEnabled();
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.GREEN);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 117, 117));



            // 创建一个数据集的数据对象
            LineData data = new LineData(set1, set2);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            //设置数据
            mDoubleLineChar.setData(data);
        }
    }

    private void initView3(){
        mPieChart = (PieChart) findViewById(R.id.mPieChart);
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
        mPieChart.setCenterText(generateCenterSpannableText());

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(40, "开心"));
        entries.add(new PieEntry(35, "舒朗"));
        entries.add(new PieEntry(15, "失落"));
        entries.add(new PieEntry(10, "抑郁"));

        //设置数据
        setData3(entries);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.BLACK);
        mPieChart.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText() {
        //原文：MPAndroidChart\ndeveloped by Philipp Jahoda
        SpannableString s = new SpannableString("心理测试反馈比例图");
        //s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        // s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        // s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    //设置数据
    private void setData3(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "心理反馈");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }

    private void initView4(){
        mPieChart1 = (PieChart) findViewById(R.id.mPieChart1);
        mPieChart1.setUsePercentValues(true);
        mPieChart1.getDescription().setEnabled(false);
        mPieChart1.setExtraOffsets(5, 10, 5, 5);

        mPieChart1.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
        mPieChart1.setCenterText(generateCenterSpannableText1());

        mPieChart1.setDrawHoleEnabled(true);
        mPieChart1.setHoleColor(Color.WHITE);

        mPieChart1.setTransparentCircleColor(Color.WHITE);
        mPieChart1.setTransparentCircleAlpha(110);

        mPieChart1.setHoleRadius(58f);
        mPieChart1.setTransparentCircleRadius(61f);

        mPieChart1.setDrawCenterText(true);

        mPieChart1.setRotationAngle(0);
        // 触摸旋转
        mPieChart1.setRotationEnabled(true);
        mPieChart1.setHighlightPerTapEnabled(true);

        //变化监听
        mPieChart1.setOnChartValueSelectedListener(this);

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(10, "失眠"));
        entries.add(new PieEntry(15, "入睡时间长"));
        entries.add(new PieEntry(60, "睡眠良好且充足"));
        entries.add(new PieEntry(15, "易醒"));

        //设置数据
        setData4(entries);

        mPieChart1.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart1.setEntryLabelColor(Color.BLACK);
        mPieChart1.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText1() {
        //原文：MPAndroidChart\ndeveloped by Philipp Jahoda
        SpannableString s = new SpannableString("睡眠测试反馈比例图");
        //s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        // s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        // s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    //设置数据
    private void setData4(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "睡眠反馈");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        mPieChart1.setData(data);
        mPieChart1.highlightValues(null);
        //刷新
        mPieChart1.invalidate();
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        // 完成之后停止晃动
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mLineChar.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    //点击事件
    @Override
    public void onClick(View v) {

    }

}
