package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 春水碧于天 on 2017/5/29.
 */

public class BarView extends View {

    private Paint mPaint;
    private Paint mPaintText;
    private Paint mPaintLine;
    private int current = 0;
    private int max = 0;
    private float currentX = 0;
    private int width;
    private int viewHeight;


    public BarView(Context context) {
        this(context,null);
    }

    public BarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(30);

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(Color.BLACK);
        mPaintLine.setStyle(Paint.Style.STROKE);




    }



    public void setValue(int current,int max) {
        this.current = current;
        this.max = max;


        Log.i("wk","setValue方法执行了");

        postInvalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        Log.i("wk","onSizeChanged方法执行了");
        currentX = (current/(max*1f))*(width-20); //计算出的x坐标

        viewHeight = h;

        Log.i("wk","currentX==>"+currentX);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.i("wk","onMeasure方法执行了");



    }

    @Override
    protected void onDraw(Canvas canvas) {



        String text = String.valueOf(current);

        Rect rect = new Rect();

        mPaintText.getTextBounds(text,0,text.length(),rect);

        int height = rect.height();

        canvas.drawText("0",0+10,viewHeight,mPaintText);
        canvas.drawRect(0+10,height,currentX,viewHeight-height,mPaint);
        canvas.drawText(text,currentX,viewHeight,mPaintText);


        canvas.drawRect(0+10,height,width-10,viewHeight-height,mPaintLine);
        Log.i("wk","onDraw方法执行了");

    }
}
