package com.example.myglide.request;

import android.content.Context;

import com.example.myglide.request.BitmapRequest;

public class Glide {
    public static Context mContextApp;
    public static BitmapRequest with(Context context){
        mContextApp = context.getApplicationContext();
        return new BitmapRequest(context);
    }
}
