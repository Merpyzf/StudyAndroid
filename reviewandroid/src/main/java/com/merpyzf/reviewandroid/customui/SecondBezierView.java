package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 春水碧于天 on 2017/5/3.
 */

public class SecondBezierView extends View {
    private Paint mPaint;
    private Point mStartPoint = new Point();
    private Point mEndPoint = new Point();
    private Point mFlagPoint = new Point();
    private Path mPath;

    public SecondBezierView(Context context) {

        this(context,null);
    }


    public SecondBezierView(Context context, @Nullable AttributeSet attrs) {

        this(context,attrs,0);

    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        InitPaint();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mStartPoint.x = w/2;
        mStartPoint.y = h/2;

        mEndPoint.x = w/2+600;
        mEndPoint.y = h/2-600;

        mFlagPoint.x = (mStartPoint.x+mEndPoint.x)/2+200;
        mFlagPoint.y = (mStartPoint.y+mEndPoint.y)/2+200;





    }

    private void InitPaint() {


        mPaint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mPath = new Path();
//        mPath.moveTo(mStartPoint.x,mStartPoint.y);
//        mPath.quadTo(mFlagPoint.x,mFlagPoint.y,mEndPoint.x,mEndPoint.y);

        mPath.cubicTo(mStartPoint.x,mStartPoint.y,mFlagPoint.x,mFlagPoint.y,mEndPoint.x,mEndPoint.y);
        canvas.drawPath(mPath,mPaint);



    }
}
