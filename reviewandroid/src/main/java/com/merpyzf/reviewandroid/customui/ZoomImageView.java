package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 春水碧于天 on 2017/5/9.
 */

public class ZoomImageView extends View {
    /**
     * 初始化状态常量
     */
    public static final int STATUS_INIT = 1;

    /**
     * 图片放大状态常量
     */
    public static final int STATUS_ZOOM_OUT = 2;

    /**
     * 图片缩小状态常量
     */
    public static final int STATUS_ZOOM_IN = 3;

    /**
     * 图片拖动状态常量
     */
    public static final int STATUS_MOVE = 4;

    /**
     * 记录当前操作的状态，可选值为STATUS_INIT、STATUS_ZOOM_OUT、STATUS_ZOOM_IN和STATUS_MOVE
     */
    private int currentStatus;
    private Bitmap sourceBitmap;


    public ZoomImageView(Context context) {
        super(context);
    }

    /**
     * ZoomImageView的构造函数，将当前的操作状态设置为STATUS_INIT
     * @param context
     * @param attrs
     */
    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        currentStatus = STATUS_INIT;

    }


    /**
     * 将待展示的图片设置进来
     * @param bitmap
     */
    public void setImageBitmap(Bitmap bitmap){

        sourceBitmap = bitmap;
        invalidate();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);






    }
}
