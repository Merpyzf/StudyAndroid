package com.merpyzf.reviewandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.merpyzf.reviewandroid.R;

public class BackDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_data);

    }

    @Override
    public void onBackPressed() {

        Intent intent = getIntent();

        intent.putExtra("name","wangke");

        //设置需要传递给上一个Activity的值
        setResult(2,intent);

        finish();

    }
}
