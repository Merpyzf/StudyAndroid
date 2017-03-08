package com.merpyzf.reviewandroid.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.receiver.SmsReceiver;

public class SmsReceiverActivity extends AppCompatActivity {

    private EditText editText;
    private SmsReceiver smsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);
        editText = (EditText) findViewById(R.id.edt_sms);

        //使用代码注册一个广播接收者
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(new SmsReceiver(editText),filter);


    }

}
