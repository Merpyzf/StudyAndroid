package com.merpyzf.reviewandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * MPAndroidChart的第一个HelloWorld示例
 */
public class MpChart02Activity extends AppCompatActivity {


    /**
     * 涉及到的类:
     *
     *      XAxis 表示X轴的类
     *
     *      Entry 为每个点的类
     *
     *      DataSet 一组Y轴上面的数据
     *
     *      LineData 整个y轴的数据
     */


    private LineChart mLineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart02);

        //1.从xml中获取linechart的引用
        mLineChart = (LineChart) findViewById(R.id.linechart);

        //2.创建一个List集合，用来存放一条折线上的所有点
        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(10,20));
        entries.add(new Entry(20,30));
        entries.add(new Entry(30,15));
        entries.add(new Entry(40,50));

        //3.将entries设置给LineDataSet数据集
        LineDataSet dataSet = new LineDataSet(entries, "Label");

        dataSet.setFillColor(Color.RED);

        //4.将上面创建的LineDataSet对象设置给LineData
        LineData lineData = new LineData(dataSet);

        //5.把lineData设置给lineChart就可以显示出来折线图了，就像把adapter设置给listview一样
        mLineChart.setData(lineData);

    }
}
