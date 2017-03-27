package com.merpyzf.reviewandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

/**
 * 广播接收者用于接收网络状态的变化
 */

public class NetStatusReceiver extends BroadcastReceiver {

    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "3G网络数据";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }
    @Override
    public void onReceive(Context context, Intent intent) {




        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {

            /**
             * 监听wifi的打开与关闭，与Wifi的连接无关
             */
            //通过WifiManager里定义的常量获取当前Wifi的状态
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);

            Log.i("wk", "wifiState:" + wifiState);
            switch (wifiState) {

                case WifiManager.WIFI_STATE_DISABLED:

                    Log.i("wk", "WifiManager.WIFI_STATE_DISABLED");

                    break;

                case WifiManager.WIFI_STATE_DISABLING:

                    Log.i("wk", "WifiManager.WIFI_STATE_DISABLING");

                    break;


            }


        }

        /**
         *
         * 监听Wifi连接的网络是否可用
         *
         * 监听wifi的连接状态即是否连接上了一个有效的路由
         */
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {

            Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);

            if (null != parcelableExtra){

                //获取联网状态的netWorkInfo对象
                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;

                NetworkInfo.State state = networkInfo.getState();

                boolean isConnected = state ==NetworkInfo.State.CONNECTED;

                if(isConnected){

                    Log.i("wk","网络连接成功");

                }else {

                    Log.i("wk","网络连接失败");

                }


            }


        }


        /**
         * 监听网络连接，包括Wifi和移动数据的打开和关闭，以及连接上的可用连接都会监听
         */
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

            if(info!=null){

                //如果当前网络连接成功并且网络连接可用

                if(NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()){

                    if(info.getType() == ConnectivityManager.TYPE_WIFI || info.getType() ==ConnectivityManager.TYPE_MOBILE){

                        Log.i("wk",getConnectionType(info.getType())+"连上");


                    }else {


                        Log.i("wk",getConnectionType(info.getType())+"断开");
                    }



                }




            }



        }





    }


}
