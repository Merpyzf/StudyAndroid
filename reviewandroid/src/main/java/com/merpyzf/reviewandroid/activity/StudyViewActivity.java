package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.merpyzf.reviewandroid.R;

public class StudyViewActivity extends AppCompatActivity {


    private ImageView imageView;
    private Scroller mScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_view);
        imageView = (ImageView) findViewById(R.id.imageView);

        int destx = 200;

        mScroller = new Scroller(this);

        int scrollX = imageView.getScrollX();


        int deltx = destx - scrollX;

        mScroller.startScroll(scrollX, 0, deltx, 1000);

        imageView.invalidate();


        int slop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(this));

        Log.i("wk", "当前设备的TouchSlop:" + slop);

        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                int left = imageView.getLeft();
                int top = imageView.getTop();


                int right = imageView.getRight();
                int bottom = imageView.getBottom();


                float translationX = imageView.getTranslationX();
                float translationY = imageView.getTranslationY();

                Log.i("wk", " transX:" + translationX + " transY:" + translationY);


                Log.i("wk", " left:" + left + " top:" + top + " right:" + right + " bootom:" + bottom);


            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) imageView.getLayoutParams();

                Params.leftMargin += 10;

                //请求重新布局当前View
                imageView.requestLayout();


            }
        });




        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });




    }




}
