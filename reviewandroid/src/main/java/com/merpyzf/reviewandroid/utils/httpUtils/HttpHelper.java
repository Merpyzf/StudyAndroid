package com.merpyzf.reviewandroid.utils.httpUtils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * 封装进行Http请求的工具类,使用HttpUrLConnection
 *
 */

public class HttpHelper {


    /**
     * 使用HttpUrlConnection进行Get请求
     *
     * @param url
     * @return 服务器返回的html
     */
    public static String httpGet(String url){

         String responseHtmlDoc = null;

        try {
            URL mUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) mUrl.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10*1000);
            urlConnection.setConnectTimeout(10*1000);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);


            urlConnection.connect();


            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream is = urlConnection.getInputStream();

                responseHtmlDoc =  IoUtil.stream2Str(is);

                return  responseHtmlDoc;



            }else if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_INTERNAL_ERROR){

                Log.i("wk","HTTP_INTERNAL_ERROR");

            }else if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND){

                Log.i("wk","HTTP_NOT_FOUND");


            }


        } catch (MalformedURLException e) {

            Log.i("wk","URL格式不正确！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


        return responseHtmlDoc;

    }






}
