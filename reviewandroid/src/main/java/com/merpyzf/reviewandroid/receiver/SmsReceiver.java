package com.merpyzf.reviewandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用Receiver监听短信到来,并截取其中的验证码
 * 2017/3/7.
 */

public class SmsReceiver extends BroadcastReceiver {

    private EditText editText;

    public SmsReceiver(EditText editText){

        this.editText = editText;

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.i("wk","action:"+action);


        if(action.equals("android.provider.Telephony.SMS_RECEIVED")){

            Object[] objects = (Object[]) intent.getExtras().get("pdus");

            for(Object obj:objects){


                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);


                String body = smsMessage.getDisplayMessageBody();

                Log.i("wk","短信内容:"+body);

                Pattern pattern = Pattern.compile("验证码:(\\d+)");

                Matcher matcher = pattern.matcher(body);


                //如果匹配到了
                if(matcher.find()) {
                    String s = matcher.group(1);

                    editText.setText(s);


                }else {


                    editText.setText(body);

                }

            }


        }


    }
}
