package com.merpyzf.reviewandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

public class ScrollActivity extends AppCompatActivity {

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        tv_test = (TextView) findViewById(R.id.tv_test);

        tv_test.scrollTo(0,100);

    }
}
