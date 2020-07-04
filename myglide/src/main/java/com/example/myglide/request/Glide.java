package com.example.myglide.request;

import android.content.Context;

import com.example.myglide.request.BitmapRequest;

public class Glide {
    public static BitmapRequest with(Context context){
        return new BitmapRequest(context);
    }
}
