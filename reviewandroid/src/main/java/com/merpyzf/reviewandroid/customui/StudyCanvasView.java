package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 春水碧于天 on 2017/5/8.
 */

public class StudyCanvasView extends View {


    public StudyCanvasView(Context context) {
        super(context);

    }

    public StudyCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public StudyCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        /**
         * 平移Canvas的坐标系需要了解的知识点:
         *  1.每次调用Canvas.draw()系列的函数进行绘图，都会产生一个全新的Canvas画布。
         *  2.如果在Draw()前调用平移、旋转等函数来对Canvas进行操作，那么这个操作是不可逆的!每次产生的画布的最新位置都是
         *  这些操作后的位置。
         *  3.在Canvas与屏幕合成时,超出屏幕范围的图像是不会显示出来的。
         */



    }
}


