package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;

/**
 *
 * Tween动画只是展现的移动的效果，控件真实的坐标不会改变，点击ImageView移动后的位置没有任何效果
 *
 */
public class TweenAnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);
        imageView = (ImageView) findViewById(R.id.imageView);

    }


    //旋转动画
    public void clickRotate(View v){

        /**
         * RotateAnimation.RELATIVE_TO_SELF :设置旋转的中心点
         * 0.5f: 相对于自身的位置
          */
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);

        //动画结束后，让view停留在动画结束的位置
        rotateAnimation.setFillAfter(true);

        //给view设置一个动画
        imageView.startAnimation(rotateAnimation);

    }


    //透明度动画
    public void clickAlpha(View v){

        /**
         * 0f 表示透明 1f 表示完全不透明
         */
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1f);
        alphaAnimation.setDuration(2000);
        imageView.startAnimation(alphaAnimation);


    }

    public void clickTranslate(View v){

        //根据坐标点的位置进行移动
//        TranslateAnimation translteAnimation = new TranslateAnimation(0,200,0,0);

        //根据自身相对父控件的位置进行移动
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT,0f,TranslateAnimation.RELATIVE_TO_PARENT,0.6f,TranslateAnimation.RELATIVE_TO_PARENT,0f,TranslateAnimation.RELATIVE_TO_SELF,0f);

        translateAnimation.setDuration(2000);

        //设置插值器
        translateAnimation.setInterpolator(new AccelerateInterpolator());

        imageView.startAnimation(translateAnimation);


    }

    //缩放动画
    public void clickScale(View v){

        /**
         * 1f x方向上当前的缩放比
         * 1.5f x方向上将要缩放的比
         * 1f y
         * 1.5f y
         *
         * ScaleAnimation.RELATIVE_TO_SELF 设置 在x轴上缩放相对的位置(相对自己)
         * 0.5f
         *
         * ScaleAnimation.RELATIVE_TO_SELF 设置 在y轴上缩放的相对位置(相对自己)
         * 0.5f
         *
         *
         */
        ScaleAnimation scaleAnimation =  new ScaleAnimation(1f,1.5f,1f,1.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setDuration(1000);

        //设置动画重复播放
        scaleAnimation.setRepeatCount(10);

        imageView.startAnimation(scaleAnimation);

        //给动画设置监听回调
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                Toast.makeText(TweenAnimationActivity.this,"动画的监听:"+"动画开始了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Toast.makeText(TweenAnimationActivity.this,"动画的监听:"+"动画结束了",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                Toast.makeText(TweenAnimationActivity.this,"动画的监听:"+"动画重复播放",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void clickSet(View v){


        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);

        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT,0f,TranslateAnimation.RELATIVE_TO_PARENT,0.6f,TranslateAnimation.RELATIVE_TO_PARENT,0f,TranslateAnimation.RELATIVE_TO_SELF,0f);

        translateAnimation.setDuration(2000);

        AnimationSet animationSet = new AnimationSet(false);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);

        animationSet.setFillBefore(true);
        animationSet.setFillAfter(true);

        imageView.startAnimation(animationSet);


    }

    public void clickRes(View v){


//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_set);
//        imageView.startAnimation(animation);


    }




}
