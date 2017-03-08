package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.merpyzf.reviewandroid.R;

public class TouchEventActivity extends AppCompatActivity {


    private int mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        //获取屏幕的宽度
        mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();


        Log.i("wk","屏幕的宽度:"+mScreenWidth);

    }


    private float mLastX = 0;
    private float mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()){


            case MotionEvent.ACTION_DOWN:


                Log.i("wk","手指按下");

                //记录手指按下时的位置坐标
                mLastX =  event.getX();
                mLastY =  event.getY();


                break;

            case MotionEvent.ACTION_UP:

                Log.i("wk","手指抬起");

                float xDistance = event.getX() - mLastX;

                float yDistance = Math.abs(event.getY() - mLastY);

                if(xDistance-yDistance>0 && xDistance>mScreenWidth/2){

                    onBackPressed();

                }



               Log.i("wk","XDistane==>"+xDistance+"YDistance==>"+yDistance);


                break;

            case MotionEvent.ACTION_MOVE:

                Log.i("wk","手指移动");

                break;




        }







        return super.onTouchEvent(event);
    }
}
