package com.merpyzf.reviewandroid.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.merpyzf.reviewandroid.R;

public class MpChart05Activity extends Activity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart05);

        mChart = (LineChart) findViewById(R.id.linechart);

        LineData data = new LineData();

        // 先增加一个空的数据，随后往里面动态添加
        mChart.setData(data);

        // x坐标轴
        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.BLACK);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);

        // 如果false，那么x坐标轴将不可见
        xl.setEnabled(true);

        // 将X坐标轴放置在底部，默认是在顶部。
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);

        // 图表左边的y坐标轴线
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);

        // 最大值
        leftAxis.setAxisMaxValue(90f);

        // 最小值
        leftAxis.setAxisMinValue(40f);

        // 不一定要从0开始
        leftAxis.setStartAtZero(false);

        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        // 不显示图表的右边y坐标轴线
        rightAxis.setEnabled(false);




    }

    public void addPoint(View v){

        addEntry();
    }

    int count =0;


    private void addEntry() {

        LineData data = mChart.getData();


        LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);

        if (set == null) {
            set = createLineDataSet();
            data.addDataSet(set);
        }


        float f = (float) ((Math.random()) * 20 + 50);


        Entry entry = new Entry(count, f);


        data.addEntry(entry, 0);

        // 像ListView那样的通知数据更新
        mChart.notifyDataSetChanged();

        // 当前统计图表中最多在x轴坐标线上显示的总量
        mChart.setVisibleXRangeMaximum(5);



         mChart.moveViewToX(set.getEntryForIndex(set.getEntryCount()-1).getX());


        count++;

    }

    // 初始化数据集，添加一条统计折线，可以简单的理解是初始化y坐标轴线上点的表征
    private LineDataSet createLineDataSet() {

        LineDataSet set = new LineDataSet(null, "动态添加的数据");


        return set;
    }
}
