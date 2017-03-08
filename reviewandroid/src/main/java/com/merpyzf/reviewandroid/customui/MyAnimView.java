package com.merpyzf.reviewandroid.customui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.merpyzf.reviewandroid.activity.PointEvaluator;
import com.merpyzf.reviewandroid.domian.Point;

/**
 * Created by Administrator on 2017/2/23.
 *
 * 自定义Evaluator使用 ValueAnimator实现自定义View的动画
 *
 **/

public class MyAnimView extends View {

    private static final float RADIUS = 50f;
    private Point currentPoint = null;
    private Paint mPaint;


    public MyAnimView(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }


    //进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(currentPoint== null){

            currentPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);

            startAnimation();

        }else{

            drawCircle(canvas);

        }


    }

   public void drawCircle(Canvas canvas){
       float x = currentPoint.getX();
       float y = currentPoint.getY();
       canvas.drawCircle(x, y, RADIUS, mPaint);


   }


    private void startAnimation() {
        //创建初始圆的圆心坐标
        Point startPoint = new Point(RADIUS, RADIUS);
        //移动后结束点位置的圆心坐标
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);

        //使用自己定义 PointEvaluator 来计算两点之间的移动动画
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);

        //设置动画监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                //获取动画的值
                currentPoint = (Point) animation.getAnimatedValue();

                //调用onDraw()进行重绘
                invalidate();
            }
        });


        anim.setDuration(5000);
        anim.start();
    }




}
