package com.merpyzf.reviewandroid.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

public class ScrollActivity extends AppCompatActivity {

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        tv_test = (TextView) findViewById(R.id.tv_test);






    }

    public void clickMove(View v){


//        tv_test.scrollTo(30*-1,30*-1);
//        tv_test.scrollBy(30*-1,30*-1);

        final float destX = 300;
        final float  destY = 300;


        ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(2000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                //动画执行的百分比的小数形式展现
                float fraction = animation.getAnimatedFraction();


                Log.i("wk","fraction:"+fraction);


                tv_test.scrollTo((int) (destX*fraction*-1),(int)(destY*fraction*-1));


            }
        });


        //开启一个动画
        animator.start();





    }
}
