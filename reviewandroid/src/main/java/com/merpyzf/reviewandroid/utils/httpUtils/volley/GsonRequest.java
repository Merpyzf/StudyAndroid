package com.merpyzf.reviewandroid.utils.httpUtils.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;

/**
 * Created by 春水碧于天 on 2017/5/21.
 */

public class GsonRequest<T> extends JsonRequest<T> {

    private Gson mGson;
    private Response.Listener<T> mListener;
    private Class<T> mClazz;


    public GsonRequest(int method, String url, String requestBody, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);

        mGson = new Gson();
        mListener = listener;
        mClazz = clazz;

    }


    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(response);

    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {

        String jsonStr = new String(networkResponse.data);

        T obj = mGson.fromJson(jsonStr, mClazz);

        return Response.success(obj, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }


}
