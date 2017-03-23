package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 简易版ViewPager
 * Scroller的使用
 */

public class ScrollerLayout extends ViewGroup {


    private Scroller mScroller;

    private int mTouchSlop;
    private int mLeftBound;
    private float mLastDownX;
    private float mLastDownY;
    private int mXMove;
    private int mRightBound;

    public ScrollerLayout(Context context) {
        super(context);

        Init(context);
    }


    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);


    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context);
    }


    private void Init(Context context) {

        //创建Scroller实例
        mScroller = new Scroller(context);

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);

        //获取当前设备能判定为滑动的最小值
        mTouchSlop = viewConfiguration.getScaledTouchSlop();


    }

    float lastDownX;
    float lastDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Boolean Intercepted = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastDownX = ev.getX();
                lastDownY = ev.getY();
                Intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:

                float deltX = ev.getX() - lastDownX;
                float deltY = ev.getY() - lastDownY;

                Intercepted  = false;


                Log.i("wk","MotionEvent.ACTION_MOVE");

                if (Math.abs(deltX) > Math.abs(deltY)) {


                    //父View拦截当前滑动的事件
                    Intercepted = true;

                } else {


                    //父View不拦截，交给子View处理
                    Intercepted = false;
                }
                lastDownX = ev.getX();
                lastDownY = ev.getY();
                break;

            case MotionEvent.ACTION_UP:

                Intercepted = false;
                break;

        }

        return true;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        View childView = null;

        for (int i = 0; i < childCount; i++) {

            childView = getChildAt(i);
            //对子View的宽高进行测量
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

        }


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if (changed) {

            Log.i("wk", "onLayout()被调用了");

            int childCount = getChildCount();

            View childView = null;


            //遍历所有的子View并进行摆放
            for (int i = 0; i < childCount; i++) {

                childView = getChildAt(i);
                //对子View进行摆放
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());

                Log.i("wk", "子View的宽度:" + childView.getMeasuredWidth());


            }


            mLeftBound = getChildAt(0).getLeft();
            mRightBound = getChildAt(childCount - 1).getLeft();


        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:


                mLastDownX = event.getRawX();

                break;

            case MotionEvent.ACTION_MOVE:

                mXMove = (int) event.getRawX();

                float deltX = mLastDownX - mXMove;

                if (getScrollX() + deltX < mLeftBound) {

                    scrollTo(mLeftBound, 0);

                    return true;
                } else if (getScrollX() + deltX > mRightBound) {

                    scrollTo(mRightBound, 0);

                    return true;

                }

                scrollBy((int) deltX, 0);


                mLastDownX = mXMove;


                break;

            case MotionEvent.ACTION_UP:


                Log.i("wk", String.valueOf(getWidth()));

                Log.i("wk", "x滑动的距离:" + getScrollX());

                int index = (getScrollX() + getWidth() + getChildAt(0).getWidth() / 2) / (getChildAt(0).getWidth()) - 1;

                Log.i("wk", "当前所在的View下标:" + index);

                int destX = index * getChildAt(index).getLeft() - getScrollX();


                mScroller.startScroll(getScrollX(), 0, destX, 0);

                invalidate();


                break;


        }


        return true;
    }


    @Override
    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {

            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }


    }
}
