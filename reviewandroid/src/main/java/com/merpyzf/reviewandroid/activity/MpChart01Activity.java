package com.merpyzf.reviewandroid.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;

public class MpChart01Activity extends Activity {

    private LineChart mChart;

    ArrayList<String> XDatas = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart01);



        XDatas.add("test1");
        XDatas.add("test2");
        XDatas.add("test3");
        XDatas.add("test4");
        XDatas.add("test5");
        XDatas.add("test6");

        mChart = (LineChart) findViewById(R.id.chart);

        mChart.setNoDataText("暂时还没有数据");

        mChart.setTouchEnabled(true);

        //设置可拖拽
        mChart.setDragEnabled(true);

        //可缩放
        mChart.setScaleEnabled(true);

        mChart.setDrawGridBackground(false);

        mChart.setPinchZoom(true);

        //设置图标的背景颜色
//        mChart.setBackgroundColor(Color.YELLOW);


        LineData data = new LineData();

        //设置数据显示的颜色
        data.setValueTextColor(Color.BLACK);

        //先增加一个空的数据，随后往里面动态添加
        mChart.setData(data);

        //图表的注解
        Legend legend = mChart.getLegend();

        //线性
        legend.setForm(Legend.LegendForm.LINE);

        legend.setTextColor(Color.BLACK);


        //x坐标轴
        XAxis xAxis = mChart.getXAxis();

        MyXFormatter myXFormatter = new MyXFormatter(XDatas);
        xAxis.setValueFormatter(myXFormatter);


        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0f);

        //?
        xAxis.setAvoidFirstLastClipping(true);

        //如果设置成false则X轴坐标不可见
        xAxis.setEnabled(true);

        //将X轴放置在底部，默认是在顶部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //获取y轴的坐标轴
        YAxis yAxis = mChart.getAxisLeft();


        yAxis.setTextColor(Color.BLACK);

        yAxis.setAxisMaxValue(90f);

        yAxis.setAxisMinValue(20f);

        //设置y轴的开始数值不一定从0开始
        yAxis.setStartAtZero(false);
        yAxis.setDrawGridLines(true);

        YAxis axisRight = mChart.getAxisRight();

        //不显示右边的y坐标轴线
        axisRight.setEnabled(false);

        addEntry();


    }


    public void clickAdd(View v){

        addEntry();



    }

    //添加进去一个坐标点
    public void addEntry(){


        LineData lineData = mChart.getData();


        //每一个LineDataSet代表一条线，每张统计图标可以同时存在若干个统计折线，这些折线像数组一样下标从0开始

        //本例只有一个，那么就是第0条折线
        LineDataSet set = (LineDataSet) lineData.getDataSetByIndex(0);

        //如果统计折线图还没有数据集则创建一条出来，如果有则跳过此处代码

        if(set ==null){

            set = createLineDataSet();

            lineData.addDataSet(set);

        }



        // 生成随机测试数
        float f = (float) ((Math.random()) * 20 + 50);

        // set.getEntryCount()获得的是所有统计图表上的数据点总量，
        // 如从0开始一样的数组下标，那么不必多次一举的加1
        Entry entry = new Entry(set.getEntryCount(),f);

        // 往linedata里面添加点。注意：addentry的第二个参数即代表折线的下标索引。
        // 因为本例只有一个统计折线，那么就是第一个，其下标为0.
        // 如果同一张统计图表中存在若干条统计折线，那么必须分清是针对哪一条（依据下标索引）统计折线添加。

        lineData.addEntry(entry, 0);

        // 像ListView那样的通知数据更新
        mChart.notifyDataSetChanged();

        // 当前统计图表中最多在x轴坐标线上显示的总量
        mChart.setVisibleXRangeMaximum(6 );

        // y坐标轴线最大值
        // mChart.setVisibleYRange(30, AxisDependency.LEFT);

        // 将坐标移动到最新
        // 此代码将刷新图表的绘图

        ILineDataSet dataSetByIndex = lineData.getDataSetByIndex(0);


        int entryCount = dataSetByIndex.getEntryCount();
        mChart.moveViewToX(entryCount);

        Log.i("wk","移动到==>"+(entryCount-5));


    }

    private LineDataSet createLineDataSet() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0,20));
        entries.add(new Entry(1,30));
        entries.add(new Entry(2,50));
        entries.add(new Entry(3,60));
        entries.add(new Entry(4,50));
        entries.add(new Entry(5,30));


        LineDataSet set = new LineDataSet(entries,"动态添加的数据");

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        //折线的颜色

        set.setColor(ColorTemplate.getHoloBlue());

        set.setCircleColor(Color.BLACK);
        set.setLineWidth(3f);
        set.setCircleSize(5f);
        set.setFillAlpha(128);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.GREEN);
        set.setValueTextColor(Color.BLACK);
        set.setValueTextColor(Color.BLACK);
        set.setDrawValues(true);

        return set;


    }


    class MyXFormatter implements IAxisValueFormatter {

        private ArrayList<String> mValues;


        public MyXFormatter(ArrayList<String> mValues) {
            this.mValues = mValues;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {


            Log.i("wk","value==>"+value);


            return mValues.get((int) (value%mValues.size()));
        }
    }

}
