package com.merpyzf.reviewandroid.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用内容解析者读取手机内短信
 *
 * 需要添加读取短信的权限
 *
 */
public class ReadSmsActivity extends AppCompatActivity {

    //用于获取短信的Uri
    private static final Uri uri = Uri.parse("content://sms/");
    private TextView tv_show_sms;
    private StringBuffer sb = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);

        tv_show_sms = (TextView) findViewById(R.id.tv_show_sms);
        sb = new StringBuffer();

        Cursor cursor = getContentResolver().query(uri, new String[]{"thread_id","date", "body","address"}, null, null, "date DESC");


        while (cursor.moveToNext()){

            int date = cursor.getInt(1);
            String body = cursor.getString(2);
            String phone = cursor.getString(3);


            Log.i("wk","phone:"+phone);

            Date mDate = new Date();

            mDate.setTime(date);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String Time = format.format(mDate);


            sb.append("号码: "+phone+"\n"+"时间: "+Time+"\n"+"内容: "+body+"\n"+"-------------------"+"\n");




        }


        cursor.close();
        tv_show_sms.setText(sb.toString());

    }



}
