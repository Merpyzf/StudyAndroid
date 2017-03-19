package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

public class ViewPagerLoop3Activity extends AppCompatActivity {


    private ImageView red_point;
    private LinearLayout content_point;
    private ViewPager viewPager;
    private int []mImages = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};
    private ArrayList<ImageView> images = null;


    //两个小圆点之间的距离
    private int point_dis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_loop3);
        InitUI();
        InitData();

        viewPager.setAdapter(new myPagerAdapter());

        viewPager.setCurrentItem(50000-(50000%images.size()));


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

                    }
                });


            }
        },1000,3000);


        red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                point_dis = content_point.getChildAt(1).getLeft() - content_point.getChildAt(0).getLeft();





            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) red_point.getLayoutParams();


                int newPosition = position % images.size();

                int left = (int) (point_dis*positionOffset)+(newPosition*point_dis);


                if(left>content_point.getChildAt(images.size()-1).getLeft()){


                    left = 0;
                }



                    params.leftMargin  = left;


                red_point.setLayoutParams(params);



            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    /**
     * 初始化数据
     */
    private void InitData() {

        images = new ArrayList<>();

        for(int i=0;i<mImages.length;i++){

            ImageView iv = new ImageView(this);
            iv.setImageResource(mImages[i]);

            images.add(iv);



            ImageView grayPoint = new ImageView(this);
            grayPoint.setImageResource(R.drawable.gray_point_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);



            if(i>0){


                params.leftMargin = 15;

                grayPoint.setLayoutParams(params);

            }





            content_point.addView(grayPoint);




        }




    }

    /**
     * 初始化UI
     */
    private void InitUI() {

        red_point = (ImageView) findViewById(R.id.red_point);

        content_point = (LinearLayout)findViewById(R.id.content_point);

        viewPager = (ViewPager) findViewById(R.id.viewPager_3);
    }



    class myPagerAdapter extends PagerAdapter{


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

            container.addView(images.get(position % images.size()));


            return images.get(position % images.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);

        }
    }
}
