package com.merpyzf.reviewandroid.activity;

import android.animation.TypeEvaluator;

import com.merpyzf.reviewandroid.domian.Point;

/**
 * Created by Administrator on 2017/2/23.
 *
 * 用于计算当前的动画值：
 *
 *  使用结束值减去开始值然后乘以 fraction系数 再加上开始值就求得当前动画的值了
 *
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        Point startPoint = (Point) startValue;

        Point endPoint = (Point)endValue;

        float x = startPoint.getX() + fraction*(endPoint.getX()-startPoint.getX());
        float y = startPoint.getY() + fraction*(endPoint.getX()-startPoint.getY());


        Point point = new Point(x,y);

        return point;

    }
}
