package com.merpyzf.reviewandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.merpyzf.reviewandroid.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }

    public void secondBezier(View v){


        startActivity(new Intent(this,SecondBezierActivity.class));

    }


    public void GraphChart(View v){


        startActivity(new Intent(this,GraphChartActivity.class));

    }



}
