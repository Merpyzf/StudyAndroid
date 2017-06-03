package com.merpyzf.reviewandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.customui.BarView;

public class ShowDrawCircleActivity extends AppCompatActivity {

    private BarView bar_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_draw_circle);
        bar_view = (BarView) findViewById(R.id.bar_view);

        bar_view.setValue(50,100);





    }
}
