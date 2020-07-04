package com.example.myglide.request;

import android.content.Context;
import android.widget.ImageView;

import com.example.myglide.uril.MD5Utils;

import java.lang.ref.SoftReference;
/*
5年进入Android行业出现图片错位问题。
 */
/**
 * 请求对象
 */
public class BitmapRequest {
    //上下文
    private Context context;
    //图片链接
    private String url;
    //ImageView
    private SoftReference<ImageView> imageView;
    //结果回调接口
    private RequestListener requestListener;
    //15年进入Android行业出现图片错位问题。
    private String urlMD5;
    //占位图
    private int resId;


    public BitmapRequest(Context context){
        this.context = context;
    }

    public BitmapRequest load(String url){
        this.url = url;
        this.urlMD5 = MD5Utils.toMD5(url);
        return this;
    }

    public BitmapRequest loading(int resId){
        this.resId = resId;
        return this;
    }

    public BitmapRequest listener(RequestListener listener){
        this.requestListener = listener;
        return this;
    }

    public void into(ImageView imageView){
        imageView.setTag(urlMD5);
        this.imageView = new SoftReference<>(imageView);
        RequestManager.getInstance().addBitmapRequest(this);
    }


    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public String getUrlMD5() {
        return urlMD5;
    }

    public int getResId() {
        return resId;
    }
}
