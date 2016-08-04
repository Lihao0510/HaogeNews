package com.lihao.haogenews;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/7/28.
 */
public class MyApplication extends Application {

    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getVolleyQueue() {
        Log.d("Lihao",mRequestQueue.toString());
        return mRequestQueue;
    }

}
