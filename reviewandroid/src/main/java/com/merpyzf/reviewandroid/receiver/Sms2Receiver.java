package com.merpyzf.reviewandroid.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

import com.merpyzf.reviewandroid.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 监听短信到来并拦截短信内容，不让广播继续向下传递
 * <p>
 * 获取短信需要 android.provider.Telephony.SMS_RECEIVED 权限
 */

public class Sms2Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {


            Object[] objects = (Object[]) intent.getExtras().get("pdus");

            for (Object obj : objects) {


                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);

                String messageBody = smsMessage.getMessageBody();

                Log.i("wk", "短信信息:" + messageBody);

                //拦截当前的广播不继续向下传递

                //Android7.0不能对短信进行拦截
                abortBroadcast();


                Pattern pattern = Pattern.compile("验证码:(\\d+)");

                Matcher matcher = pattern.matcher(messageBody);

                Log.i("wk", "" + matcher.find());
                Log.i("wk", "" + matcher.group(1));


                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

                builder.setTicker(matcher.group());
                builder.setContentTitle("新信息");
                builder.setContentText(messageBody);
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.image));
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setAutoCancel(true);
                Notification notification = builder.build();

                manager.notify(1, notification);


            }

        }


    }
}
