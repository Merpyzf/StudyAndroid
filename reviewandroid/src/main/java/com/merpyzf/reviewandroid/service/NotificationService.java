package com.merpyzf.reviewandroid.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.activity.AdActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class NotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        //返回IBindler对象
        return new MyBindler();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //显示一个Notification
    public  void showNotification(){

        /**
         * 通过PendingIntent获取一个跳转到Activity的延时意图
         */
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), AdActivity.class), 0);

        //创建一个notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentInfo("这是一个info");

        //Android7.0上不显示ticker中的文字
        builder.setTicker("这是一个ticker");

        builder.setContentTitle("这是一个标题");
        builder.setContentText("这是一个内容");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        builder.setContentIntent(pendingIntent);

        //设置通知自动关闭
        builder.setAutoCancel(true);

        //给通知设置声音
//        builder.setSound(Uri.fromFile(new File("/System/media/audio/ringtones/Luna.ogg")));

        //通知的时候进行震动
        builder.setVibrate(new long[]{0,1000,1000,1000});

        //封装长文字信息
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("hahahahahahhauchuehfuehufhrfurhfyrhfurhfurfhurhfurhfurhfurkfkkrmfjrfjrnfjrnfrfhrfrhfrhfrh"));

        //用于设置一张大图片
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.image)));


        //设置通知的重要程度
//        builder.setPriority(NotificationCompat.PRIORITY_MAX);
//        builder.setPriority(NotificationCompat.PRIORITY_LOW);


        Notification notification = builder.build();

        notificationManager.notify(1,notification);

        //通过notificationManager进行显式的取消通知的显示
//        notificationManager.cancel(1);





    }

    public class MyBindler extends Binder implements method{


        @Override
        public void callNotification() {

            showNotification();
        }
    }

 interface  method{

     void callNotification();
 }

}
