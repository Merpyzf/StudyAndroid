package com.merpyzf.reviewandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.merpyzf.reviewandroid.activity.ShowDrawCircleActivity;
import com.merpyzf.reviewandroid.activity.SlidingConflictActivity;

/**
 * 自定义View系列练习
 */
public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    /**
     * 1.简单了解
     *  绘制一个圆
     */
    public void clickDrawCircle(View v){

        startActivity(new Intent(this,ShowDrawCircleActivity.class));

    }

    /**
     * 2.滑动事件冲突的处理
     * @param v
     */
    public void clickSlidingConflict(View v){

        startActivity(new Intent(this, SlidingConflictActivity.class));

    }

}
