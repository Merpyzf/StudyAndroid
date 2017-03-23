package com.merpyzf.reviewandroid.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.service.MusicService;

public class StudyServiceActivity extends AppCompatActivity {


    private MusicService.MyIBinder mBinder;
    private MyServiceConn myServiceConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_service);
        Intent intent = new Intent(this, MusicService.class);
        myServiceConn = new MyServiceConn();
        startService(intent);
        bindService(intent,myServiceConn, Context.BIND_AUTO_CREATE);

        Log.i("wk","调用onCreate");

    }

    public void playMusic(View v){

        mBinder.callPlayMusic();

    }

    public void pauseMusic(View v){

        mBinder.callPauseMusic();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除Servicebind
        unbindService(myServiceConn);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mBinder.callPlayMusic();


    }

    @Override
    protected void onResume() {
        super.onResume();


        Log.i("wk","调用onResume");

        if(mBinder!=null) {
            mBinder.callPauseMusic();
        }

    }



    class MyServiceConn implements ServiceConnection {

        //异步调用，此方法在onResume方法执行结束后调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mBinder = (MusicService.MyIBinder) service;

            Log.i("wk","调用onServiceConnected");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
