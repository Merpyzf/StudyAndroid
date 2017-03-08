package com.merpyzf.reviewandroid.utils.httpUtils;

import android.os.Handler;
import android.os.Message;

/**
 *
 * 进行Http请求的对象
 * @author wangke
 *
 */

public abstract class HttpRequest {

    private String mUrl;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {


            getResponse((String) msg.obj);

        }
    };


    public HttpRequest(String Url){

        this.mUrl = Url;

        getRequest();


    }

    public void getRequest(){


        new Thread(new Runnable() {
            @Override
            public void run() {


                String htmlDoc = HttpHelper.httpGet(mUrl);

                Message msg = new Message();

                msg.obj = htmlDoc;

                mHandler.sendMessage(msg);

            }
        }).start();

    }


    public  abstract void getResponse(String htmlDoc);


}
