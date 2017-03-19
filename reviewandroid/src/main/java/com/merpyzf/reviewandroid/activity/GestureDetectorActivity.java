package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

/**
 * 手势识别的学习
 *
 * 1.创建自己的手势兼听类，实现GestureDetector.OnGestureListener里的方法
 *
 * 2.创建 GestureDetector对象，传入 上一步实现的手势兼听类
 *
 * 3.实现View类的OnTouchListener 接口
 *
 * 4.重写 onTouch方法，让GestureDetector接收处理onTouchEvent事件
 *
 * 5.给要进行手势兼听的类设置 setOnTouchListener(this)
 *
 *
 */
public class GestureDetectorActivity extends AppCompatActivity implements View.OnTouchListener {

    private GestureDetector mGestureDetector;
    private ImageView imageView;
    private int []Images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    private int mIndex = 0;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnTouchListener(this);


        detector = new GestureDetector(this, new MyGestureDetector());


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return detector.onTouchEvent(motionEvent);
    }


    class MyGestureDetector implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{


        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

            Log.i("wk"," onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {


            Log.i("wk","onDoubleTap");


            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {

            Log.i("wk"," onDoubleTapEvent");


            return true;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {

            Log.i("wk"," onDown");



            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

            Log.i("wk"," onShowPress");

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {

            Log.i("wk"," onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.i("wk"," onScroll");

            return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {


            Log.i("wk"," onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {




            return true;
        }
    }


}

