package com.merpyzf.reviewandroid.activity;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.merpyzf.reviewandroid.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 监听手机短信到来，并识别其中的验证码
 *
 * 遇到的问题:onChange回调会在数据库改变时调用多次
 *
 */
public class SmsVerificationCodeActivity extends AppCompatActivity {

    private static final Uri uri = Uri.parse("content://sms/");
    private EditText edt_code;
    private ContentObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification_code);
        edt_code = (EditText) findViewById(R.id.edt_code);


        mObserver = new myContentObserver(new Handler());
        getContentResolver().registerContentObserver(uri,true, mObserver);

    }

    //监听短信数据空中的变化
    class myContentObserver extends ContentObserver {

        public myContentObserver(Handler handler) {
            super(handler);
        }


        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);

            Log.i("wk","短信来了:"+getNewSms());

            String SmsBody = getNewSms().trim();


            Pattern p = Pattern.compile("验证码:(\\d{4})");

            Matcher m = p.matcher(SmsBody);

            if(m.find()){

                String code = m.group(1);
                edt_code.setText("验证码:"+code);

            }else {

                edt_code.setText(SmsBody);

            }



        }
    }


    //获取最新的一条短信信息
    private String getNewSms() {

        Cursor cursor = getContentResolver().query(uri, new String[]{"_id","body"}, null, null, "date DESC");

        cursor.moveToNext();

        String smsBody = cursor.getString(1);
        int id = cursor.getInt(0);

        Log.i("wk","ID====>"+id);

        return smsBody;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消注册
        getContentResolver().unregisterContentObserver(mObserver);


    }
}
