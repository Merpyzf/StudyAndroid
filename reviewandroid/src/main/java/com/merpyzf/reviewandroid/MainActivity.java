package com.merpyzf.reviewandroid;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.activity.AboutActivity;
import com.merpyzf.reviewandroid.activity.AdActivity;
import com.merpyzf.reviewandroid.activity.AssetsActivity;
import com.merpyzf.reviewandroid.activity.AsyncTaskActivity;
import com.merpyzf.reviewandroid.activity.CaptureScreenActivity;
import com.merpyzf.reviewandroid.activity.CodeLayoutActivity;
import com.merpyzf.reviewandroid.activity.ContentObserverActivity;
import com.merpyzf.reviewandroid.activity.CopyBitMapActivity;
import com.merpyzf.reviewandroid.activity.DouBanMovieActivity;
import com.merpyzf.reviewandroid.activity.FrameByFrameActivity;
import com.merpyzf.reviewandroid.activity.GestureDetectorActivity;
import com.merpyzf.reviewandroid.activity.GraphActivity;
import com.merpyzf.reviewandroid.activity.ImplicitIntentActivity;
import com.merpyzf.reviewandroid.activity.ItemSlidingListViewActivity;
import com.merpyzf.reviewandroid.activity.LocationActivity;
import com.merpyzf.reviewandroid.activity.ObserverSmsActivity;
import com.merpyzf.reviewandroid.activity.OnTouchActivity;
import com.merpyzf.reviewandroid.activity.OpenCamearActivity;
import com.merpyzf.reviewandroid.activity.PhotoAlbumActivity;
import com.merpyzf.reviewandroid.activity.PropertyAnimationActivity;
import com.merpyzf.reviewandroid.activity.ReadContactsActivity;
import com.merpyzf.reviewandroid.activity.ReadSmsActivity;
import com.merpyzf.reviewandroid.activity.SQLiteActivity;
import com.merpyzf.reviewandroid.activity.SaveImageActivity;
import com.merpyzf.reviewandroid.activity.ScaleImageActivity;
import com.merpyzf.reviewandroid.activity.ScrollActivity;
import com.merpyzf.reviewandroid.activity.ShowAllSensorsActivity;
import com.merpyzf.reviewandroid.activity.ShowChartActivity;
import com.merpyzf.reviewandroid.activity.SmsReceiverActivity;
import com.merpyzf.reviewandroid.activity.SmsVerificationCodeActivity;
import com.merpyzf.reviewandroid.activity.StudyBaseWidgetActivity;
import com.merpyzf.reviewandroid.activity.StudyFileActivity;
import com.merpyzf.reviewandroid.activity.StudyFragmentActivity;
import com.merpyzf.reviewandroid.activity.StudyGsonActivity;
import com.merpyzf.reviewandroid.activity.StudyRxJavaActivity;
import com.merpyzf.reviewandroid.activity.StudyServiceActivity;
import com.merpyzf.reviewandroid.activity.StudyUiActivity;
import com.merpyzf.reviewandroid.activity.Test2ViewPagerLoopActivity;
import com.merpyzf.reviewandroid.activity.TestContentProviderActivity;
import com.merpyzf.reviewandroid.activity.TestSqlActivity;
import com.merpyzf.reviewandroid.activity.TouchEventActivity;
import com.merpyzf.reviewandroid.activity.TweenAnimationActivity;
import com.merpyzf.reviewandroid.activity.UseValueAnimatorActivity;
import com.merpyzf.reviewandroid.activity.VideoViewActivity;
import com.merpyzf.reviewandroid.activity.ViewPagerLoop3Activity;
import com.merpyzf.reviewandroid.activity.ViewPagerLoopActivity;
import com.merpyzf.reviewandroid.game.SnakeActivity;
import com.merpyzf.reviewandroid.receiver.ScreenReceiver;
import com.merpyzf.reviewandroid.receiver.Sms2Receiver;
import com.merpyzf.reviewandroid.service.NotificationService;

public class MainActivity extends AppCompatActivity {


    private ScreenReceiver screenReceiver;

    private LinearLayout ll_content;
    private NotificationService.MyBindler bindler;
    private Intent intent;
    private MyConn mConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);


        //对于一些频繁触发的事件，需要在代码中进行动态注册
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");

        screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, intentFilter);


        //Bind用于后台Notification的Service
        Intent  intent = new Intent(this, NotificationService.class);
        mConn = new MyConn();
        bindService(intent,mConn, Context.BIND_AUTO_CREATE);

        /**
         * 设置当前Activity只能竖屏，而不能进行横竖屏切换
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        /*
            动态申请权限
         */
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},0);

        }else {

            //如果已经有接收短信的权限则动态注册短信到来的广播

            IntentFilter intentFilterSms = new IntentFilter();
            intentFilterSms.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(new Sms2Receiver(),intentFilterSms);

        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case 0:

                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){


                    IntentFilter intentFilterSms = new IntentFilter();
                    intentFilterSms.addAction("android.provider.Telephony.SMS_RECEIVED");
                    registerReceiver(new Sms2Receiver(),intentFilterSms);

                }else {

                    Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show();



                }


                break;


        }



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



    public void clickCustomView(View v){

        startActivity(new Intent(this, CustomViewActivity.class));



    }


    public void clickStudyService(View v){

        startActivity(new Intent(this, StudyServiceActivity.class));

    }

    public void clickVideoView(View v){

        startActivity(new Intent(this, VideoViewActivity.class));

    }


    /**
     * 录音使用学习
     * @param v
     */
    public void clickMediaRecorder(View v){




    }

    /**
     * 绘制折线图
     * @param v
     */
    public void clickLineChartView(View v){


        startActivity(new Intent(this, ShowChartActivity.class));

    }

    /**
     * 测试截取当前屏幕
     * @param v
     */
    public void clickCaptureScreen(View v){

        startActivity(new Intent(this, CaptureScreenActivity.class));

    }


    /**
     * Gson的使用
     * @param v
     */
    public void clickGson(View v){


        startActivity(new Intent(this,StudyGsonActivity.class));


    }


    /**
     * 在Activity中调用Service中的Notification
     * @param v
     */
    public void clickNotification(View v){



        if(bindler!=null) {

            bindler.callNotification();
        }

    }


    public void clickFile(View v){


        startActivity(new Intent(this, StudyFileActivity.class));



    }


    /**
     * 长按ImageView进行图片保存+截取当前手机的屏幕
     * @param v
     */
    public void clickSaveImage(View v){

        startActivity(new Intent(this, SaveImageActivity.class));

    }





    public void clickCodeLayout(View v){


        startActivity(new Intent(this, CodeLayoutActivity.class));

    }


    public void clickGraph(View v){

        startActivity(new Intent(this, GraphActivity.class));

        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        int screenHeight = wm.getDefaultDisplay().getHeight();
        Log.i("wk","屏幕的宽度:"+screenWidth+"屏幕的高度:"+screenHeight);


    }


    public void clickAllSensor(View v){

        startActivity(new Intent(this, ShowAllSensorsActivity.class));

    }

    /**
     * 获取图片的尺寸
     * @param v
     */
    public void clickBitMapSize(View v){

        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(getResources(),R.drawable.ico,options);

        //获取图片的宽高
        int height = options.outHeight;
        int width = options.outWidth;

        Log.i("wk","图片的宽度:"+width+"图片的高度:"+height);


    }

    /**
     * 拷贝原图的副本
     * @param v
     */
    public void clickCopyBitMap(View v){


        startActivity(new Intent(this, CopyBitMapActivity.class));

    }


    /**
     * 定位api的使用
     * @param v
     */
    public void clickLocation(View v){

        startActivity(new Intent(this, LocationActivity.class));

    }




    /**
     * 实现ServiceConnection接口，用于BindService
     */
    class MyConn implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            bindler = (NotificationService.MyBindler) service;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
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







    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(screenReceiver);
        //解除绑定Service
        unbindService(mConn);
    }
}
