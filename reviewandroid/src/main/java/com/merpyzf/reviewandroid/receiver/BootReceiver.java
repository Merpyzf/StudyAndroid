package com.merpyzf.reviewandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.merpyzf.reviewandroid.MainActivity;

/**
 * Created by wangke on 2017/2/22.
 * 广播接收者的使用
 */

public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"执行",Toast.LENGTH_SHORT).show();

        String action = intent.getAction();

        if(action.equals("android.intent.action.BOOT_COMPLETED")){


            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);

        }

        Log.i("wk","action:"+action);
        Toast.makeText(context,"action:"+action,Toast.LENGTH_SHORT).show();




    }





}
