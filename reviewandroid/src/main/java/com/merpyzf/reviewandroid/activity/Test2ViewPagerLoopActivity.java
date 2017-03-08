package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.merpyzf.reviewandroid.R;

import java.util.ArrayList;

public class Test2ViewPagerLoopActivity extends AppCompatActivity {


    private ViewPager mViewPager;

    //用于摆放小圆点的线性布局
    private LinearLayout ll_point;

    //小红点
    private ImageView lv_red_point;

    private int []images = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five};
    private ArrayList<ImageView> mImageViewList;
    private int mPointDistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2_view_pager_loop);
        InitUI();

        InitData();

        mViewPager.setAdapter(new viewPagerAdapter());

        mViewPager.setCurrentItem(5000000-(5000000%mImageViewList.size()));



        //设置ViewPager滑动的监听事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) lv_red_point.getLayoutParams();


                int newPosition = position%mImageViewList.size();


                params.leftMargin = (int) (mPointDistance*positionOffset)+newPosition*mPointDistance;

                lv_red_point.setLayoutParams(params);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        lv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                mPointDistance = ll_point.getChildAt(1).getLeft() - ll_point.getChildAt(0).getLeft();

                Log.i("wk","两个小圆点之间的距离"+ mPointDistance);


            }
        });







    }

    /**
     * 初始化数据
     */
    private void InitData() {

        mImageViewList = new ArrayList<ImageView>();

        for(int i=0;i<images.length;i++){

            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(images[i]);
            mImageViewList.add(iv);

            ImageView point_gray = new ImageView(this);

            point_gray.setImageResource(R.drawable.gray_point_shape);


            //灰色的小圆点的父布局是LinearLayout
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            if(i>0) {
                params.leftMargin = 20;
            }

            point_gray.setLayoutParams(params);
            ll_point.addView(point_gray);
        }

    }

    /**
     * 初始化布局
     */
    private void InitUI() {


        mViewPager = (ViewPager) findViewById(R.id.viewPager_2);
        ll_point = (LinearLayout)findViewById(R.id.ll_point);
        lv_red_point = (ImageView) findViewById(R.id.iv_point_red);


    }


    class viewPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            int newPosition = position%mImageViewList.size();

            container.addView(mImageViewList.get(newPosition));


            return mImageViewList.get(newPosition);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);


        }
    }

}
