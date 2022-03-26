package com.example.bks;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleySingleton {
    private RequestQueue requestQueue;
    private static volleySingleton mInstance;

    private volleySingleton(Context context) {
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());

    }
    public static synchronized volleySingleton getmInstance(Context context){
        if(mInstance==null){
            mInstance=new volleySingleton(context);
        }
        return  mInstance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
