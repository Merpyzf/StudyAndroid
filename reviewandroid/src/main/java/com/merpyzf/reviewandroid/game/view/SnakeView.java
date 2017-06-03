package com.merpyzf.reviewandroid.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.merpyzf.reviewandroid.game.bean.Snake;


/**
 * Snake
 * @author wangke
 */
public class SnakeView extends View {

    private Snake mSnake;
    private int flag = 3;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            invalidate();

        }
    };

    public SnakeView(Context context) {
        super(context);
    }

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnakeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSnake(Snake snake){

        this.mSnake = snake;
//
//        Timer timer = new Timer();
//
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//
//
//            }
//        },0,100);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mHandler.sendEmptyMessage(1);

                handler.postDelayed(this,10);

            }
        },100);



    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        mSnake.drawSnake(canvas);

    }
}
