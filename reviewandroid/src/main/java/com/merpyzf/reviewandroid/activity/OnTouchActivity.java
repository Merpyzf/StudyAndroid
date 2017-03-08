package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

/**
 *
 * onTouch() 与 onTouchEvent()的区别:
 *
 *  1.onTouch()方法是View的onTouchListener接口中的方法
 *
 *  2.onTouchEvent()方法是重写的Activity中的方法
 *
 *  3.两个方法都是在dispatchTocuhEvent中的调用，onTouch优先于
 *  onTouchEvent方法执行，如果在onTouch()通过返回true将事件消费掉，
 *  onTouchEvent()方法将不会执行。
 *
 *
 *
 */
public class OnTouchActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        mImageView = (ImageView) findViewById(R.id.imageView_t);

        //当前view的状态必须是可用的，onTouch()方法才会响应触摸事件
        mImageView.setEnabled(true);

        mImageView.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:

                Log.i("wk","onTouch == >手指按下了");

                break;

            case MotionEvent.ACTION_UP:

                Log.i("wk","onTouch == >手指抬起了");

                break;

            case MotionEvent.ACTION_MOVE:

                Log.i("wk","onTouch == >手指移动了");

                break;

        }

        //当一个动作执行完成之后如果返回false后面的移动抬起就不会被响应，此时的触摸事件向下分发，因此要监听连续的 点击、移动、手指抬起，要返回True
        //将事件在该层消费，不让其继续向下传递。
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                Log.i("wk","onTouchEvent == >手指按下了");


                break;

            case MotionEvent.ACTION_UP:

                Log.i("wk","onTouchEvent == >手指抬起了");

                break;

            case MotionEvent.ACTION_MOVE:

                Log.i("wk","onTouchEvent == >手指移动了");

                break;
        }

        return true;
    }
}
