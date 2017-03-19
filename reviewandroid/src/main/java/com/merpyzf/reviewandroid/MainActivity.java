package com.merpyzf.reviewandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.activity.AboutActivity;
import com.merpyzf.reviewandroid.activity.AdActivity;
import com.merpyzf.reviewandroid.activity.AssetsActivity;
import com.merpyzf.reviewandroid.activity.AsyncTaskActivity;
import com.merpyzf.reviewandroid.activity.ContentObserverActivity;
import com.merpyzf.reviewandroid.activity.DouBanMovieActivity;
import com.merpyzf.reviewandroid.activity.FrameByFrameActivity;
import com.merpyzf.reviewandroid.activity.GestureDetectorActivity;
import com.merpyzf.reviewandroid.activity.ImplicitIntentActivity;
import com.merpyzf.reviewandroid.activity.ItemSlidingListViewActivity;
import com.merpyzf.reviewandroid.activity.ObserverSmsActivity;
import com.merpyzf.reviewandroid.activity.OnTouchActivity;
import com.merpyzf.reviewandroid.activity.OpenCamearActivity;
import com.merpyzf.reviewandroid.activity.PhotoAlbumActivity;
import com.merpyzf.reviewandroid.activity.PropertyAnimationActivity;
import com.merpyzf.reviewandroid.activity.ReadContactsActivity;
import com.merpyzf.reviewandroid.activity.ReadSmsActivity;
import com.merpyzf.reviewandroid.activity.SQLiteActivity;
import com.merpyzf.reviewandroid.activity.ScaleImageActivity;
import com.merpyzf.reviewandroid.activity.ScrollActivity;
import com.merpyzf.reviewandroid.activity.SmsReceiverActivity;
import com.merpyzf.reviewandroid.activity.SmsVerificationCodeActivity;
import com.merpyzf.reviewandroid.activity.StudyBaseWidgetActivity;
import com.merpyzf.reviewandroid.activity.StudyFragmentActivity;
import com.merpyzf.reviewandroid.activity.StudyRxJavaActivity;
import com.merpyzf.reviewandroid.activity.StudyUiActivity;
import com.merpyzf.reviewandroid.activity.Test2ViewPagerLoopActivity;
import com.merpyzf.reviewandroid.activity.TestContentProviderActivity;
import com.merpyzf.reviewandroid.activity.TestSqlActivity;
import com.merpyzf.reviewandroid.activity.TouchEventActivity;
import com.merpyzf.reviewandroid.activity.TweenAnimationActivity;
import com.merpyzf.reviewandroid.activity.UseValueAnimatorActivity;
import com.merpyzf.reviewandroid.activity.ViewPagerLoop3Activity;
import com.merpyzf.reviewandroid.activity.ViewPagerLoopActivity;
import com.merpyzf.reviewandroid.game.SnakeActivity;
import com.merpyzf.reviewandroid.receiver.ScreenReceiver;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //对于一些频繁触发的事件，需要在代码中进行动态注册
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(new ScreenReceiver(), intentFilter);


    }


    //补间动画的使用学习
    public void clickTweenAnimation(View v) {

        Intent intent = new Intent(this, TweenAnimationActivity.class);

        startActivity(intent);

//      overridePendingTransition(R.anim.animation_set,R.anim.animation_set);
    }


    //属性动画的使用学习
    public void clickPropertyAnimation(View v) {
        Intent intent = new Intent(this, PropertyAnimationActivity.class);
        startActivity(intent);
    }


    //ValueAnimator的高级用法
    public void clickValueAnimator(View v) {

        Intent intent = new Intent(this, UseValueAnimatorActivity.class);

        startActivity(intent);

    }


    //使用TouchEvent实现手势操作
    public void clickTouchEvent(View v) {


        Intent intent = new Intent(this, TouchEventActivity.class);

        startActivity(intent);

    }


    //手势识别器的使用
    public void clickGestureDetector(View v) {


        Intent intent = new Intent(this, GestureDetectorActivity.class);
        startActivity(intent);


    }

    /**
     * intent调用系统相机，并返回拍摄照片
     *
     * @param v
     */
    public void clickIntent(View v) {

        Intent intent = new Intent(this, OpenCamearActivity.class);

        startActivity(intent);

    }

    /**
     * 两个Fragment的通信以及生命周期相关
     *
     * @param v
     */
    public void clickFragment(View v) {

        Intent intent = new Intent(this, StudyFragmentActivity.class);

        startActivity(intent);
    }


    /**
     * onTouch()与onTouchEvent()的区别
     *
     * @param v
     */
    public void clickOnTouch(View v) {


        Intent intent = new Intent(this, OnTouchActivity.class);

        startActivity(intent);

    }

    /**
     * 隐式意图 开启Activity与系统应用
     *
     * @param v
     */
    public void clickImplicitIntent(View v) {


        Intent intent = new Intent(this, ImplicitIntentActivity.class);
        startActivity(intent);


    }


    /**
     * 打开系统相册 并将选择的图片显示到imageview
     *
     * @param v
     */
    public void clickPhotoAlbum(View v) {

        Intent intent = new Intent(this, PhotoAlbumActivity.class);

        startActivity(intent);

    }


    /**
     * AsyncTask的使用，从网络加载图片并显示到ImageView
     *
     * @param v
     */
    public void clickAsyncTask(View v) {

        Intent intent = new Intent(this, AsyncTaskActivity.class);
        startActivity(intent);
    }

    /**
     * 图片显示自适应ImageView
     *
     * @param v
     */
    public void clickScaleImage(View v) {

        Intent intent = new Intent(this, ScaleImageActivity.class);

        startActivity(intent);

    }

    /**
     * SQLite数据库的使用
     *
     * @param v
     */
    public void clickSQLite(View v) {

        Intent intent = new Intent(this, SQLiteActivity.class);

        startActivity(intent);

    }

    /**
     * 读取手机短信
     *
     * @param v
     */
    public void clickReadSms(View v) {

        Intent intent = new Intent(this, ReadSmsActivity.class);

        startActivity(intent);

    }


    /**
     * 读取短信联系人
     * <p>
     * 赛前准备:内容解析者的巩固学习
     *
     * @param v
     */
    public void clickReadContacts(View v) {


        Intent intent = new Intent(this, ReadContactsActivity.class);

        startActivity(intent);

        String fileDir = this.getFilesDir().toString();


        Log.i("wk", "文件路径:" + fileDir);


    }


    /**
     * 获取短信中的验证码
     *
     * @param v
     */
    public void clickSMS(View v) {


        Intent intent = new Intent(this, SmsVerificationCodeActivity.class);

        startActivity(intent);

    }

    //从资产目录下读取
    public void clickAssets(View v) {

        Intent intent = new Intent(this, AssetsActivity.class);

        startActivity(intent);

    }

    public void clickProvider(View v) {

        Intent intent = new Intent(this, TestContentProviderActivity.class);

        startActivity(intent);


    }

    /**
     * ActionBar和DrawerLayout的使用
     *
     * @param v
     */
    public void clickAd(View v) {

        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);


    }


    public void clickParams(View v) {


    }


    public void clickViewPagerLoop(View v) {

        Intent intent = new Intent(this, ViewPagerLoopActivity.class);
        startActivity(intent);

    }


    /**
     * 自测ViewPager轮询并添加可移动的指示器
     *
     * @param v
     */
    public void clickViewPagerLoop1(View v) {


        Intent intent = new Intent(this, Test2ViewPagerLoopActivity.class);

        startActivity(intent);


    }


    /**
     * ViewPager轮询图，并添加指示器 第三遍
     *
     * @param v
     */
    public void clickViewPagerLoop2(View v) {

        Intent intent = new Intent(this, ViewPagerLoop3Activity.class);

        startActivity(intent);

    }


    /**
     * Snake Game
     *
     * @param v
     */
    public void clickViewSnake(View v) {

        Intent intent = new Intent(this, SnakeActivity.class);
        startActivity(intent);


    }


    public void clickContentObserver(View v) {


        startActivity(new Intent(this, ContentObserverActivity.class));

    }


    public void clickAboutActivity(View v) {

        startActivity(new Intent(this, AboutActivity.class));


    }

    /**
     * 使用 SqliteOpenHelper实现增删改查的练习
     *
     * @param v
     */
    public void clickSql(View v) {


        Intent intent = new Intent(this, TestSqlActivity.class);

        startActivity(intent);


    }


    /**
     * 监听短信到来
     * <p>
     * 内容观察者的使用练习
     *
     * @param v
     */
    public void ObserverSms(View v) {


        startActivity(new Intent(this, ObserverSmsActivity.class));


    }


    /**
     * PopupWindow的使用
     *
     * @param v
     */
    public void clickShowPopupWindow(View v) {


        showPopupWindow();


    }



    public void clickDialog(View v){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("这是一个提示的标题");
        builder.setMessage("我是一个内容");


        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this,"忽略",Toast.LENGTH_SHORT).show();

            }
        });


        AlertDialog alertDialog = builder.create();


        //实现提示窗体透明的代码

        Window window = alertDialog.getWindow();

        WindowManager.LayoutParams attributes = window.getAttributes();

        attributes.alpha = 0.6f;

        window.setAttributes(attributes);

        alertDialog.show();

    }






    /**
     * 显示PopupWindow
     */
    public void showPopupWindow() {


        //创建一个显示PopupWindow内容的布局
        View contentView = View.inflate(this, R.layout.popup_layout, null);

        //创建一个PopupWindow对象
        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


        TextView tv_pc = (TextView) contentView.findViewById(R.id.pop_computer);

        tv_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(MainActivity.this, "计算机", Toast.LENGTH_SHORT).show();

                mPopupWindow.dismiss();

            }
        });


        //设置用于显示PopupWindow的根不具
        View rootView = View.inflate(this, R.layout.activity_main, null);

        //设置PopupWindow要显示的根布局，以及要显示的位置
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);


        mPopupWindow.setFocusable(true);

    }


    /**
     * 豆瓣电影Top205展示
     * <p>
     * listView的练习使用
     * json解析的练习
     *
     * @param v
     */
    public void clickDouban(View v) {

        Intent intent = new Intent(this, DouBanMovieActivity.class);
        startActivity(intent);
    }

    /**
     * 使用广播监听短信到来，并截取验证码的内容
     *
     * @param v
     */
    public void clickSmsReceiver(View v) {

        Intent intent = new Intent(this, SmsReceiverActivity.class);
        startActivity(intent);
    }

    public void clickStudyUI(View v) {

        Intent intent = new Intent(this, StudyUiActivity.class);
        startActivity(intent);

    }

    public void clickBaseWidget(View v){


        Intent intent = new Intent(this, StudyBaseWidgetActivity.class);

        startActivity(intent);


    }



    public void clickFrameByFrame(View v){



        startActivity(new Intent(this, FrameByFrameActivity.class));




    }


    public void clickRxJava(View v){

        startActivity(new Intent(this, StudyRxJavaActivity.class));

    }



    public void clickViewDragHelpe(View v){


        startActivity(new Intent(this, ItemSlidingListViewActivity.class));



    }


    public void clickScroll(View v){

        startActivity(new Intent(this, ScrollActivity.class));


    }










    //用于创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    //监听菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.action_1:

                Toast.makeText(this,"菜单被点击了!",Toast.LENGTH_SHORT).show();

                break;


        }

        return true;
    }
}
