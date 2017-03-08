package com.merpyzf.reviewandroid.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

/**
 *
 * PropertyAnimation属性动画学习:
 *
 * 动画的类型:
 *
 *  1."rotation":旋转动画
 *  2.
 *
 *  ofFloat()中的第二个参数可以传"任意值",因为ObjectAnimator在设计
 *  的时候就没有针对于View来进行设计，而是针对于任意对象的，它所负责的工作
 *  就是不断地向某个对象中的某个属性进行赋值，然后对象根据属性值的改变再来
 *  决定如何展现出来
 *
 *
 *
 */
public class PropertyAnimationActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    /**
     * 旋转动画
     * @param v
     */
    public void clickRotate(View v){


        /**
         * 参数1:动画作用的View
         * 参数2:动画的类型
         *
         */

        /**
         * 这段代码的意思是ObjectAnimator会帮我们不断改变imageView中 rotation属性的当前值，
         * objectAnimator内部的工作机制并不是直接对我们传入的属性名进行操作的，而是会去寻找这个
         * 属性名对应的get和set方法
         *
         * TypeEvaluator:动画平滑过度的实现:
         *          内置了一个FloatEvaluator
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"rotation",0,360,0);

        animator.setInterpolator(new AccelerateInterpolator());

        animator.setDuration(3000);

        animator.start();

    }


    /**
     * 位移动画
     * @param v
     */
    public void clickTranslate(View v){


        //沿着X轴的方向进行移动
//        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationX",imageView.getX(),-500f,imageView.getX());

        //沿着Y轴的方向进行移动
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationY",imageView.getY(),500f,imageView.getX());

        animator.setDuration(3000);

        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        animator.start();





    }


    /**
     * 缩放动画
     * @param v
     */
    public void clickScale(View v){

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"scaleX",1,10f,1);

        objectAnimator.setDuration(1000);

        objectAnimator.start();



    }

    /**
     * 渐变动画
     * @param v
     */
    public void clickAlpha(View v){

        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"alpha",1f,0f,1f);
        animator.setDuration(2000);
        animator.start();


    }


    /**
     * 组合动画
     * @param v
     */
    public void clickSet(View v){

        ObjectAnimator animRotate = ObjectAnimator.ofFloat(imageView,"rotation",0f,360f);

        ObjectAnimator animTranslation = ObjectAnimator.ofFloat(imageView,"translationY",imageView.getY(),500,imageView.getY());

        ObjectAnimator animScale = ObjectAnimator.ofFloat(imageView,"scaleY",1f,0f,1f);


        //创建一个动画集合
        AnimatorSet animatorSet = new AnimatorSet();

        AnimatorSet.Builder animBuilder = animatorSet.play(animRotate);

        /*after(Animator anim)   将现有动画插入到传入的动画之后执行
        after(long delay)   将现有动画延迟指定毫秒后执行
        before(Animator anim)   将现有动画插入到传入的动画之前执行
        with(Animator anim)   将现有动画和传入的动画同时执行*/

        animBuilder.with(animTranslation).after(animScale);

        animatorSet.setDuration(3000);

        animatorSet.start();


    }



    /**
     *使用XML编写属性动画:
     * (优点:解决动画的复用问题)
     * 1.在Res目录下创建animator文件夹,所有属性动画的XMl文件应
     * 存放在这个文件夹中。
     * 2.
     *<animator>  对应代码中的ValueAnimator
     *<objectAnimator>  对应代码中的ObjectAnimator
     *<set>  对应代码中的AnimatorSet
     *
     * 3.ordering
     *  进行设置动画播放的顺序
     *
     *
     *
     *
     * @param v
     */
    public void clickRes(View v){

        //从XML中加载动画资源
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.anim_file);

        //给对象设置动画
        animator.setTarget(imageView);
        animator.start();




    }






    /**
     *
     * ValueAnimator的用法
     *
     *  本身不提供任何动画效果，用来产生具有一定规律的数字，从而让调用者控制动画
     *  实现的过程。
     *
     * ObjectAnimator继承自ValueAnimator
     *
     * @param v
     */
    public void clickValueAnimator(View v){

        //ofFloat的参数可以传入多个值
        ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        anim.setDuration(1000);
        anim.start();
        //设置动画播放延时的时间
        anim.setStartDelay(1000);
        //设置动画播放重复的次数
        anim.setRepeatCount(2);
        //设置动画的差值器
        anim.setInterpolator(new AccelerateInterpolator());

        //动画的监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {


                float currentValue = (float) valueAnimator.getAnimatedValue();

                Log.i("wk","当前值:"+currentValue);

            }
        });


        //动画所处动作的监听
        anim.addListener(new Animator.AnimatorListener() {

            //动画开始时调用
            @Override
            public void onAnimationStart(Animator animator) {

                Log.i("wk","动画开始执行了");

            }

            //动画结束时调用
            @Override
            public void onAnimationEnd(Animator animator) {

                Log.i("wk","动画结束了");

            }

            //动画取消时调用
            @Override
            public void onAnimationCancel(Animator animator) {

                Log.i("wk","动画取消了");

            }

            //动画重复执行时调用
            @Override
            public void onAnimationRepeat(Animator animator) {


                Log.i("wk","动画重复执行了");
            }
        });


        //AnimatorListenerAdapter适配器类,使用这个类就可以解决掉实现接口繁琐的问题了
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

    }

}
