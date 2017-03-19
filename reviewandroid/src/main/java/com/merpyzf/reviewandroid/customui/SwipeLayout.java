package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SwipeLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelp;
    private View mLeftView;
    private View mRightView;

    public SwipeLayout(Context context) {
        super(context);
        InitView();
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        InitView();
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView();
    }


    enum SwipeState{
        Open,Close;
    }

    private SwipeState currentState = SwipeState.Close;//当前默认是关闭状态


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //获取左边的View
        mLeftView = getChildAt(0);

        //获取右边的View
        mRightView = getChildAt(1);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean result = mViewDragHelp.shouldInterceptTouchEvent(ev);

        //如果当前有打开的,并且手指点击的位置不是当前打开的条目，则拦截事件交给onTouchEvent处理
        if(!SwipeLayoutManager.getInstance().isShouldSwipe(this)){
            //先关闭已经打开的layout
            SwipeLayoutManager.getInstance().closeCurrentLayout();

            result = true;
        }

        return result;
    }


    private float downX;
    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        //如果当前有打开的，则下面的逻辑不能执行（条目的滑动事件）
        if(!SwipeLayoutManager.getInstance().isShouldSwipe(this)){

            //不要父View拦截当前条目滑动的事件
            requestDisallowInterceptTouchEvent(true);
            return true;
        }

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                downX = event.getX();
                downY = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = event.getX() - downX;
                float moveY = event.getY() - downY;


                //水平移动滑动条目时禁止ListView拦截事件
                if(Math.abs(moveX)>Math.abs(moveY)){


                    requestDisallowInterceptTouchEvent(true);


                }

                //更新按下点的位置
                downX = event.getX();
                downY = event.getY();

                break;

        }

        //将触摸事件传递给ViewDragHelper

        mViewDragHelp.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mLeftView.layout(0,0,mLeftView.getWidth(),mLeftView.getHeight());
        mRightView.layout(mLeftView.getWidth(),0,mLeftView.getWidth()+mRightView.getWidth(),mRightView.getHeight());



    }

    private void InitView() {

        mViewDragHelp = ViewDragHelper.create(this,callBack);


    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        //检测触摸事件的条件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {


            return child == mLeftView || child == mRightView;
        }


        //处理垂直滑动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {

            return 0;
        }

        //处理水平滑动
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            Log.i("wk","left ==>"+left);

            //确定左边View的可滑动边界
            if(child == mLeftView){

                if(left<-mLeftView.getWidth()/2){

                    left = -mLeftView.getWidth()/2;

                }else if(left>0) {

                    left=0;
                }

                //确定右边View可滑动的边界
            }else if(child == mRightView){


                if(left < mLeftView.getWidth()/2) {

                    left = mLeftView.getWidth()/2;

                }else if(left == mLeftView.getWidth()){

                    left = mLeftView.getWidth();

                }

            }

            return left;
        }


        //当点击的View被松开后执行此方法
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            //计算自动打开/关闭的边界
            int rightBound  =  mLeftView.getWidth()/2+mLeftView.getWidth()/4;

            if(mRightView.getLeft()>rightBound){

                Log.i("wk","关闭菜单");

                close();

            }else if(mRightView.getLeft()<rightBound){


                Log.i("wk","打开菜单");


                open();
            }

            Log.i("wk","边界: "+rightBound+"rightLeft: "+mRightView.getLeft());


        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

            if(changedView == mLeftView){

                mRightView.layout(mRightView.getLeft()+dx,mRightView.getTop()+dy,mRightView.getRight()+dx,mRightView.getBottom()+dy);

            }else if(changedView == mRightView){


                mLeftView.layout(mLeftView.getLeft()+dx,mLeftView.getTop()+dy,mLeftView.getRight()+dx,mLeftView.getBottom()+dy);

            }

            //判断开和关闭的逻辑
            if(mLeftView.getLeft()==0 && currentState!=SwipeState.Close){
                //说明应该将state更改为关闭
                currentState = SwipeState.Close;
                SwipeLayoutManager.getInstance().clearCurrentLayout();

                /**
                 * 状态回调
                 */
                if(onSwipeStateListener!=null){

                    onSwipeStateListener.close();

                }


            }else if (mLeftView.getLeft()==-mLeftView.getWidth()/2 && currentState!=SwipeState.Open) {
                //说明应该将state更改为开
                currentState = SwipeState.Open;
                //当前的Swipelayout已经打开，需要让Manager记录一下下
                SwipeLayoutManager.getInstance().setSwipeLayout(SwipeLayout.this);

                /**
                 * 状态回调
                 */
                if(onSwipeStateListener!=null){

                    onSwipeStateListener.open();

                }


            }

        }


    };

    public void close() {

        mViewDragHelp.smoothSlideViewTo(mLeftView,0,0);
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);

    }

    public void open() {

        mViewDragHelp.smoothSlideViewTo(mLeftView,-(mLeftView.getWidth()/2),0);
        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);


    }

    @Override
    public void computeScroll() {

        if(mViewDragHelp.continueSettling(true)){

            ViewCompat.postInvalidateOnAnimation(this);

        }

    }

    private onSwipeStateListener onSwipeStateListener;


    public void setOnSwipeStateListener(onSwipeStateListener onSwipeStateListener){

        this.onSwipeStateListener = onSwipeStateListener;

    }




    public interface onSwipeStateListener{

        void open();
        void close();

    }




}



