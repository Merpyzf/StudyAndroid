package com.merpyzf.reviewandroid.utils.httpUtils.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by 春水碧于天 on 2017/5/21.
 */

public abstract class VolleyHelper<T> {

    private static RequestQueue mRequestQueue = null;

    public VolleyHelper(Context context, String url, String requestBody) {

        mRequestQueue = QueueSingleton.getInstance(context);




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                Response(jsonObject);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                error(volleyError.toString());


            }
        });

        mRequestQueue.add(jsonObjectRequest);


    }

    public VolleyHelper(Context context, String url, String requestBody, Class<T> clazz) {

        mRequestQueue = QueueSingleton.getInstance(context);

        GsonRequest<T> gsonRequest = new GsonRequest<>(Request.Method.POST, url, requestBody, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T obj) {


                Response(obj);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                error(volleyError.toString());

            }
        });


        mRequestQueue.add(gsonRequest);


    }


    public abstract void Response(JSONObject jsonObject); //根据URL获取网络数据中的JsonObject

    public abstract void Response(T obj);

    public abstract void error(String error);

}

class QueueSingleton {

    private static RequestQueue mQueue = null;

    private QueueSingleton() {

    }

    public static RequestQueue getInstance(Context context) {


        if (mQueue == null) {

            synchronized (QueueSingleton.class) {

                if (mQueue == null) {

                    mQueue = Volley.newRequestQueue(context);

                }

            }


        }

        return mQueue;
    }
}

