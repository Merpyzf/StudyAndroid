package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 春水碧于天 on 2017/5/8.
 */

public class GradienterView extends View {

    private int width;
    private int height;
    private Paint mPaint;

    public float x;
    public float y;

    public float xScale;
    public float yScale;

    public GradienterView(Context context) {

        this(context,null);
    }

    public GradienterView(Context context, @Nullable AttributeSet attrs) {

        this(context,attrs,0);
    }

    public GradienterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        InitPaint();
    }

    private void InitPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        x = xScale*width;
        y = yScale*height;

        //将坐标系平移
        canvas.translate(width/2,height/2);

        mPaint.setColor(Color.RED);
        canvas.drawCircle((float) ((3.1/180)*width), 0,20,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(x,y,20,mPaint);

    }
}
