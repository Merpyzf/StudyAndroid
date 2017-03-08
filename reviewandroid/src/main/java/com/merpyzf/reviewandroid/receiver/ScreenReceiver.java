package com.merpyzf.reviewandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/6.
 */

public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();


        Log.i("wk",action);


        if(action == "android.intent.action.SCREEN_ON"){

            Log.i("wk","屏幕开启了");


        }else if(action == "android.intent.action.SCREEN_OFF"){

            Log.i("wk","屏幕关闭了");

        }


    }
}
