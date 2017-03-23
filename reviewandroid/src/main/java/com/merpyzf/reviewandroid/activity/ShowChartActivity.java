package com.merpyzf.reviewandroid.activity;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.customui.ChartView;

import java.util.ArrayList;
import java.util.Random;

public class ShowChartActivity extends AppCompatActivity {

    private ChartView chartView;
    private ArrayList<Point> points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_chart);
        chartView = (ChartView) findViewById(R.id.chartView);

        Random random = new Random();

        points = new ArrayList<Point>();
        points.add(new Point(20,40));
        points.add(new Point(20,20));
        points.add(new Point(30,5));
        points.add(new Point(40,18));

        chartView.SetDataAndDraw(5,5,points);
    }
}
