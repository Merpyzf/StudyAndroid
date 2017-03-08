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
import java.util.Timer;
import java.util.TimerTask;

public class ViewPagerLoopActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ArrayList<ImageView> mIvLists;
    private ImageView mRedPoint;
    private LinearLayout mLinearpoint;
    private int mDistance;
    private int []mImages = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_loop);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLinearpoint = (LinearLayout)findViewById(R.id.linear_point);
        mRedPoint = (ImageView)findViewById(R.id.iv_red_point);

        //初始化数据
        InitData();

        mViewPager.setAdapter(new myViewPagerAdapter());

        mViewPager.setCurrentItem(5000000 - (5000000%mIvLists.size()));


        //在定时器中进行轮训
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                    }
                });


            }
        },3000,3000);



        //设置ViewPager的滑动监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.i("wk","position: "+position+"移动的百分比: "+positionOffset+"移动的像素: "+positionOffsetPixels);


                int newPosition = position % mImages.length;

                //计算小红点的移动距离
                int distance = (int) ((mDistance * positionOffset)+newPosition*mDistance);


                Log.i("wk","小红点移动的距离: "+distance);

                 RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRedPoint.getLayoutParams();



                if(distance>mLinearpoint.getChildAt(mLinearpoint.getChildCount()-1).getLeft()){


                    distance = 0;

                }


                params.leftMargin = distance;

                mRedPoint.setLayoutParams(params);


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        //监听onLayout方法是否执行完
        mRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {



            @Override
            public void onGlobalLayout() {


                mDistance = mLinearpoint.getChildAt(1).getLeft()-mLinearpoint.getChildAt(0).getLeft();

                mRedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);





            }
        });



    }

    private void InitData() {

        mIvLists = new ArrayList<ImageView>();

        for(int i=0;i<mImages.length;i++){

            ImageView iv = new ImageView(this);

            iv.setImageResource(mImages[i]);

            //设置图片在ImageView中的缩放模式
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            mIvLists.add(iv);


            //根据图片的个数填充小圆点
            ImageView point = new ImageView(this);

            point.setImageResource(R.drawable.gray_point_shape);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            if(i>0){

                params.leftMargin = 10;
                point.setLayoutParams(params);
            }

            mLinearpoint.addView(point);

        }

    }


    class myViewPagerAdapter extends PagerAdapter{


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

            int newPosition = position % mIvLists.size();

            Log.i("wk","position: "+newPosition);

            container.addView(mIvLists.get(newPosition));

            return mIvLists.get(newPosition);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);

        }

    }
}
