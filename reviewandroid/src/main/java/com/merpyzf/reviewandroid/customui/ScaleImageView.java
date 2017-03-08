package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by wangke on 2017/2/25.
 *
 * 小图片放大到ImageView控件大小
 * 大图片按比例缩小
 *
 * 图片的缩放未完成
 *
 */

public class ScaleImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {

    private ScaleGestureDetector mScaleGestureDetector = null;
    private Matrix matrix;
    private int mImgWidth;
    private int mImgHeight;
    private int mViewWidth;
    private int mViewHeight;
    private final float[] matrixValues = new float[9];
    private float minScale = 1.0f;
    private float maxScale = 4.0f;
    private float initScale;


    public ScaleImageView(Context context) {
        super(context);
        Init(context);
    }


    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context);
    }


    private void Init(Context context) {

        mScaleGestureDetector = new ScaleGestureDetector(context, this);

    }

    /**
     * 当View被添加到Window中，被绘制之前回调
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //设置onGlobalLayout的监听,在View被添加到window中设置
        getViewTreeObserver().addOnGlobalLayoutListener(this);

    }

    /**
     * 当View从Window中remove时回调
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        //当View被从window中移除时，此时移除OnLayoutListener的监听
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {

        //获取ImageView控件的宽高
        mViewWidth = getWidth();
        mViewHeight = getHeight();

        Log.i("wk", "width:" + mViewWidth + "height:" + mViewHeight);

        Drawable drawable = getDrawable();

        //获取ImageView中设置的图片的宽高
        mImgWidth = drawable.getIntrinsicWidth();
        mImgHeight = drawable.getIntrinsicHeight();

        float scale = 1.0f;

        if (drawable == null) return;


        if (mImgWidth < mViewWidth && mImgHeight > mViewHeight) {

            scale = mViewHeight * 1.0f / mImgHeight;

        } else if (mImgWidth > mViewWidth && mImgHeight < mViewHeight) {

            scale = mViewWidth * 1.0f / mImgWidth;

        } else if ((mImgWidth < mViewWidth && mImgHeight < mViewHeight) || (mImgWidth > mViewWidth && mImgHeight > mViewHeight)) {

            scale = Math.min((mViewHeight * 1.0f / mImgHeight), (mViewWidth * 1.0f / mImgWidth));

        }

        initScale = scale;


        //将缩放的图片移动到ImageView的中心点
        float Xdistance = mViewWidth * 1.0f / 2 - mImgWidth / 2;
        float Ydistance = mViewHeight * 1.0f / 2 - mImgHeight / 2;


        matrix = new Matrix();

        matrix.postScale(scale, scale, mImgWidth / 2.0f, mImgHeight / 2.0f);

        //将缩放的图片移动到ImageView的中心点
        matrix.postTranslate(Xdistance, Ydistance);

        setImageMatrix(matrix);

        setOnTouchListener(this);


        Log.i("wk", "mImgWidth:" + mImgWidth + "mImgHeight:" + mImgHeight);
    }


    //缩放手势的监听
    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {

        //获取当前的缩放因数
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        Log.i("wk", "onScale==> scaleFactor:" + scaleFactor);
        float currentScale = getScale();

        float scale = 0.0f;

        Log.i("wk", "currentScale===>" + currentScale);

        matrix.postScale(scaleFactor, scaleFactor, mViewWidth / 2.0f, mViewHeight / 2.0f);

        setImageMatrix(matrix);
        return false;
    }

    private float getScale() {

        matrix.getValues(matrixValues);

        return matrixValues[Matrix.MSCALE_X];


    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        //获取当前的缩放因数
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        Log.i("wk", "onScaleBegin==> scaleFactor:" + scaleFactor);

        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        //获取当前的缩放因数
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        Log.i("wk", "onScaleEnd==> scaleFactor:" + scaleFactor);


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mScaleGestureDetector.onTouchEvent(motionEvent);
    }
}
