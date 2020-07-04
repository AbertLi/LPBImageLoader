package com.example.myglide.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

//执行请求的线程
public class BitmapDispatcher extends Thread{
    //创建线程切换Handler
    private Handler mHandler = new Handler(Looper.getMainLooper());
    //请求队列
    private LinkedBlockingQueue<BitmapRequest> mQueue;
    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> mQueue){
        this.mQueue = mQueue;
    }
    @Override
    public void run() {
        while (true){
            //获取请求队像。
            try {
                BitmapRequest bitmapRequest = mQueue.take();
                showLoadingImage(bitmapRequest);
                Bitmap bitmap = findBitmap(bitmapRequest);
                showBitmap(bitmapRequest,bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showBitmap(final BitmapRequest bitmapRequest, final Bitmap bitmap) {
        if (bitmapRequest.getImageView() != null && bitmap != null
                && bitmapRequest.getImageView().getTag().equals(bitmapRequest.getUrlMD5())) {
               mHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       bitmapRequest.getImageView().setImageBitmap(bitmap);
                   }
               });
        }
    }


    private Bitmap findBitmap(BitmapRequest request){
        return getBitmap(request.getUrl());
    }

    public void showLoadingImage(final BitmapRequest request){
        if (request.getResId()>0&&request.getImageView()!=null){
            mHandler.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void run() {
                    request.getImageView().setImageResource(request.getResId());
                }
            });
        }
    }

    public Bitmap getBitmap(String urls) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urls);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
            // 处理结果
        } catch (IOException e) {
            Log.e("TAG","转发出错，错误信息："+e.getLocalizedMessage()+";"+e.getClass());
        }finally {
            // 5. 断开连接
            if (null != httpURLConnection){
                try {
                    httpURLConnection.disconnect();
                }catch (Exception e){
                    Log.e("TAG","httpURLConnection 流关闭异常："+ e.getLocalizedMessage());
                }
            }
        }
        return null;
    }
}
