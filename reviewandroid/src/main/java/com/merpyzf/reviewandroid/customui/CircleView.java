package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import com.merpyzf.reviewandroid.R;

;

/**
 * 自定义View示例 绘制一个简单的圆
 * 直接继承自View不支持：
 *  padding属性
 *  wrap_content
 *  需要自己实现
 */

public class CircleView extends View{
    private Paint mPaint = null;
    private int color;
    private Scroller mScrollr;


    public CircleView(Context context) {
        super(context);
        Init();

    }



    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        color = typedArray.getColor(R.styleable.CircleView_circle_color, Color.YELLOW);

        typedArray.recycle();


        Init();


    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }


    private void Init() {
        //设置画笔抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);

        mScrollr = new Scroller(getContext());

        int scrollX = getScrollX();

        Log.i("wk","scrollX==>"+scrollX);

        int deltaX = 100-scrollX;

        mScrollr.startScroll(scrollX,0,-200,0,3000);

        invalidate();




    }


    @Override
    public void computeScroll() {

        if(mScrollr.computeScrollOffset()){

            scrollTo(mScrollr.getCurrX(),mScrollr.getCurrY());

            postInvalidate();
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //获取当前View的宽高

        int width = getWidth();
        int height = getHeight();


        //获取各个方向的padding值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //计算当前要绘制的圆的半径,绘制时考虑padding值
        int radius = Math.min(width-paddingLeft-paddingRight,height-paddingTop-paddingBottom)/2;

        //绘制圆
        canvas.drawCircle(width/2,height/2,radius,mPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));


    }

    private int measureHeight(int heightMeasureSpec) {

        int result = 0;
        /**
         * 通过MeasureSpec获取测量模式和尺寸
         */
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        switch (mode){

            //如果是精确值模式
            case MeasureSpec.EXACTLY:
                result = size;
                break;

            case MeasureSpec.AT_MOST:

                result = 300;

                break;

        }




        return result;


    }


    private int measureWidth(int widthMeasureSpec) {

        int result = 0;

        /**
         * 通过MeasureSpec获取测量模式和尺寸
         */
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        switch (mode){

            //如果是精确值模式
            case MeasureSpec.EXACTLY:
                result = size;
                break;

            case MeasureSpec.AT_MOST:

                result = 300;

                break;

        }




        return result;

    }



}
