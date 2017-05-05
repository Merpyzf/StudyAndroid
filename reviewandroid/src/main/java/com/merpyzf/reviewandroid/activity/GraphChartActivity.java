package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.customui.ChartViewPlus;

import java.util.ArrayList;

public class GraphChartActivity extends AppCompatActivity {

    private ChartViewPlus chartViewPlus;
    private ArrayList<String> xRawDatas;
    private ArrayList<Double> yList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_chart);

        chartViewPlus = (ChartViewPlus) findViewById(R.id.chartViewPlus);
        chartDateDatas();

        chartViewPlus.setData(xRawDatas,yList);



    }


    private void chartDateDatas() {
        xRawDatas = new ArrayList<String>();
        xRawDatas.add("05:19");
        xRawDatas.add("05:20");
        xRawDatas.add("05:21");
        xRawDatas.add("05:22");
        xRawDatas.add("05:23");
        xRawDatas.add("05:24");
        xRawDatas.add("05:25");

        yList = new ArrayList<Double>();
        yList.add(4.2);
        yList.add(5.3);
        yList.add(6.6);
        yList.add(5.9);
        yList.add(4.5);
        yList.add(3.0);
        yList.add(1.2);

    }


}
