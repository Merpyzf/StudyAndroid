package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.merpyzf.reviewandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Volley网络请求框架的使用
 *
 */
public class VolleyActivity extends AppCompatActivity {

    private Context context;
    private RequestQueue mQueue;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        //RequestQueue是一个请求队列对象，它用来缓存所有的HTTP请求，然后按照一定的算法并发的发出这些请求
        context = this;
        mQueue = Volley.newRequestQueue(context);

        imageView = (ImageView) findViewById(R.id.imageView);


//        StringRequest();
//        upLoadjson();

//        getZhiHuDaily();

        studyImageRequest();


    }


    public void StringRequest(){


        StringRequest stringRequest = new StringRequest("http://blog.csdn.net/guolin_blog/article/details/17482095/", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                Log.i("wk", "获取的响应==>" + s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


            }
        });

        mQueue.add(stringRequest);


    }



    public void upLoadjson(){

        String url = "http://172.22.21.230:8080/transportservice/action/GetTrafficLightConfigAction.do";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, "{\"TrafficLightId\":\"1\", \"UserName\":\"Z0004\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    String redTime = jsonObject.getString("RedTime");

                    Log.i("wk","红灯时间:"+redTime);




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


        mQueue.add(jsonObjectRequest);

    }



    public void getZhiHuDaily(){

        String url = "http://news-at.zhihu.com/api/4/news/latest";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Log.i("wk", "zhuhu==>" + jsonObject.toString());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


        mQueue.add(jsonObjectRequest);

    }


    public void studyImageRequest(){


        final String imageUrl = "http://img.hb.aicdn.com/ca50c15601fb5276466215abebb76fad16af9ef1d21f-w8vGIU_fw658";
        ImageRequest imageRequest = new ImageRequest(imageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mQueue.add(imageRequest);
    }









}
