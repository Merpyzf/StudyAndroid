package com.merpyzf.reviewandroid.customui;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SwipeLayoutManager {

    private SwipeLayoutManager(){};
    private static SwipeLayoutManager mInstance = new SwipeLayoutManager();

    public static SwipeLayoutManager getInstance(){


        return mInstance;
    }

    //记录当前打开的layout
    private SwipeLayout currentLayout;

    public void setSwipeLayout(SwipeLayout layout){

        this.currentLayout = layout;

    }

    /**
     * 清空当前所记录的已经打开的layout
     */
    public void clearCurrentLayout(){
        currentLayout = null;
    }

    /**
     * 关闭当前已经打开的item
     */
    public void closeCurrentLayout(){

        if(currentLayout!= null){

            currentLayout.close();

        }


    }

    /**
     *判断当前是否应该能够滑动，如果没有打开的，则可以滑动。
     * 如果有打开的，则判断打开的layoue和当前按下的layout是否是同一个
     * @param layout
     * @return
     */
    public boolean isShouldSwipe(SwipeLayout layout){

        if(currentLayout==null){


            return true;
        }else {


            //如果是同一个则可以进行滑动，如果不是同一个则不能滑动
            return currentLayout == layout;
        }





    }


}
