package com.merpyzf.reviewandroid.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.merpyzf.reviewandroid.R;

/**
 * 可分页加载的ListView
 * @author wangke
 *
 * 2017/3/5.
 */

public class PagingListView extends ListView implements AbsListView.OnScrollListener{

    //正在加载中
    private static final int LOADING = 1;

    //加载完成
    private static final int LOADING_COMPLETE = 0;

    //当前的状态
    private int currentState = 1;


    private View mFootView;

    //脚布局的高度
    private int mFootViewHeight;


    public PagingListView(Context context) {
        super(context);
        InitFootView();
        setOnScrollListener(this);
    }



    public PagingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitFootView();
        setOnScrollListener(this);

    }

    public PagingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitFootView();

        setOnScrollListener(this);
    }



    //初始化脚布局
    private void InitFootView() {

        mFootView = View.inflate(getContext(), R.layout.foot_load_item, null);


        mFootView.measure(0,0);

        mFootViewHeight = mFootView.getMeasuredHeight();

        addFooterView(mFootView);


    }

    /**
     * 滑动状态改变的监听
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     *
     * 滑动时一直调用
     * @param view
     * @param firstVisibleItem 当前能看见的第一条的item的id
     * @param visibleItemCount 当前可见的item的总数
     * @param totalItemCount 所有的item
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


        if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount>0){



            if(currentState == LOADING){

                //显示脚布局，正在加载中
                ShowFootView();

                //放置进行多次加载
                currentState = LOADING_COMPLETE;

                onLoadingListener.onLoading();


            }


        }



    }


    public void setLoadingComplete(){


        currentState = LOADING;

    }


    /**
     * 隐藏脚布局的方法
     */
    public void hideFootView(){

        mFootView.setPadding(0,-mFootViewHeight,0,0);

    }

    /**
     * 显示脚布局
     */
    public void ShowFootView(){

        mFootView.setPadding(0,0,0,0);

    }

    private onLoadingListener onLoadingListener;

    public void setOnLoadingListener(onLoadingListener onLoadingListener){


        this.onLoadingListener = onLoadingListener;

    }



    public interface onLoadingListener{

        void onLoading();

    }

}
