package com.merpyzf.reviewandroid.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.merpyzf.reviewandroid.R;

/**
 * 在Service中进行播放音乐Bind Service的使用
 */
public class MusicService extends Service {

    private MediaPlayer mediaPlayer;
    private AssetFileDescriptor assetFileDescriptor;

    public MusicService() {

    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i("wk","onBind");
        return new MyIBinder();


    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("wk","onCreate()");

        //打开Raw目录下的音频文件
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lz);

    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

        Log.i("wk","onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.i("wk","unbindService");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.release();

        Log.i("wk","onDestroy");
    }


    public void playMusic(){

        Log.i("wk","播放音乐");


        mediaPlayer.start();

    }

    public void pauseMusic(){

        Log.i("wk","暂停音乐");

        mediaPlayer.pause();

    }



    public class MyIBinder extends Binder{

        public void callPlayMusic(){
            playMusic();
        }

        public void callPauseMusic(){

            pauseMusic();

        }


    }


}
