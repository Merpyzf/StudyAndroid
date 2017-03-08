package com.merpyzf.reviewandroid.activity;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

/**
 * 使用内容观察者获取发送过来的信息内容
 */
public class ObserverSmsActivity extends AppCompatActivity {
    private TextView tv_show_sms;
    private MySmsObserver mySmsObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_sms);

        tv_show_sms = (TextView) findViewById(R.id.tv_show_sms);


        mySmsObserver = new MySmsObserver(new Handler());

        getContentResolver().registerContentObserver(Uri.parse("content://sms/"),true, mySmsObserver);


    }

    //通过继承ContentObserver
    class MySmsObserver extends ContentObserver{


        public MySmsObserver(Handler handler) {
            super(handler);
        }


        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.i("wk","短信内容发生变化了");


            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), new String[]{"_id,body"}, null, null, "date DESC");

            cursor.moveToFirst();


            String id = cursor.getString(0);
            String body = cursor.getString(1);

            tv_show_sms.setText(" id: "+id+" body: "+body);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册内容观察者
        getContentResolver().unregisterContentObserver(mySmsObserver);

    }
}
