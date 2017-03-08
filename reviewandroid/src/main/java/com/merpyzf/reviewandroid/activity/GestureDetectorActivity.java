package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        imageView = (ImageView) findViewById(R.id.imageView);

        mGestureDetector = new GestureDetector(this, new MyGestureListener());


        imageView.setOnTouchListener(this);

        imageView.setLongClickable(true);
        imageView.setClickable(true);

        imageView.setImageResource(Images[0]);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return mGestureDetector.onTouchEvent(motionEvent);

    }


    class MyGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


        //按下屏幕就会触发
        @Override
        public boolean onDown(MotionEvent motionEvent) {


            Log.i("wk", "==>onDown()");

            return false;
        }


        //如果是按下的时间超过瞬间，而且在按下的时候没有松开或者是拖动时触发
        @Override
        public void onShowPress(MotionEvent motionEvent) {

            Log.i("wk", "==>onShowPress()");

        }

        //轻击一下屏幕，立刻抬起来，才会有这个触发
        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {


            Log.i("wk", "==>onSingleTapUp()");

            return false;
        }

        //在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {

            Log.i("wk", "==>onScroll()");

            return false;
        }

        //长按触摸屏，超过一定时长，就会触发这个事件
        @Override
        public void onLongPress(MotionEvent motionEvent) {

            Log.i("wk", "==>onLongPress()");

        }


        //滑屏，用户按下触摸屏、快速移动后松开
        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.i("wk", "==>onFling()");

            //手指按下的位置
            float downX = motionEvent.getX();
            float downY = motionEvent.getY();

            //手指抬起的位置
            float upX = motionEvent1.getX();
            float upY = motionEvent1.getY();

            float xDistance = downX - upX;
            float yDistance = downY - upY;

            Log.i("wk","downX:"+downX+"upX:"+upX);

            //判断当前手指滑动的方向，判断如果滑动的像素大于200并且是偏向水平方向时执行
            if(xDistance>0 && Math.abs(xDistance)>200 &&  Math.abs(xDistance)-Math.abs(yDistance)>0 ){

                Toast.makeText(GestureDetectorActivity.this,"右滑",Toast.LENGTH_SHORT).show();

                if(mIndex == Images.length-1){

                    Toast.makeText(GestureDetectorActivity.this,"已经滑动到最后一张图片了",Toast.LENGTH_SHORT).show();
                }else {

                    imageView.setImageResource(Images[mIndex]);

                    mIndex++;
                }


            }else if(xDistance<0 && Math.abs(xDistance)>200 && Math.abs(xDistance)-Math.abs(yDistance)>0) {


                Toast.makeText(GestureDetectorActivity.this,"左滑",Toast.LENGTH_SHORT).show();

                if(mIndex == 0){

                    Toast.makeText(GestureDetectorActivity.this,"已经滑动到第一张张图片了",Toast.LENGTH_SHORT).show();
                }else {

                    imageView.setImageResource(Images[mIndex]);

                    mIndex--;
                }


            }

            Log.i("wk", "motionEvent:" + motionEvent.getX());

            Log.i("wk", "motionEvent1:" + motionEvent1.getX());


            return true;
        }


        //用来判定该次点击是SingleTap而不是DoubleTap，如果连续点击两次就是DoubleTap手势，如果只点击一次，系统等待一段时间后没有收到第二次点击则判定该次点击为SingleTap而不是DoubleTap，然后触发SingleTapConfirmed事件。
        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {


            Log.i("wk", "==>onSingleTapConfirmed()");

            return false;
        }

        //双击事件
        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {


            Log.i("wk", "==>onDoubleTap()");

            return false;


        }


        //双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件；下图是双击一下的Log输出
        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {


            Log.i("wk", "==>onDoubleTapEvent()");
            return false;
        }
    }
}

