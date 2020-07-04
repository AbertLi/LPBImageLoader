package com.example.myglide.request;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {
    private static class Holder{
        private static RequestManager requestManager = new RequestManager();
    }
    //请求队列
    private LinkedBlockingQueue<BitmapRequest>mQueue;
    //
    private BitmapDispatcher [] bitmapDispatchersArray;

    private RequestManager(){
        mQueue = new LinkedBlockingQueue<>();
        stop();
        createAndOpenThread();
    }

    /**
     * 创建线程数组，并且打开线程。
     */
    private void createAndOpenThread() {
        int conut = Runtime.getRuntime().availableProcessors();
        bitmapDispatchersArray = new BitmapDispatcher[conut];
        for (int j = 0; j < conut; j++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(mQueue);
            bitmapDispatcher.start();
            bitmapDispatchersArray[j] = bitmapDispatcher;
        }
    }

    /**
     *  关闭所有线程
     */
    public void stop(){
        if (bitmapDispatchersArray!=null&&bitmapDispatchersArray.length>0){
            for (BitmapDispatcher bitmapDispatcher:bitmapDispatchersArray) {
                if (!bitmapDispatcher.isInterrupted()){//沒有休息
                    bitmapDispatcher.interrupt();//休息
                }
            }
        }
    }

    public static RequestManager getInstance(){
        return Holder.requestManager;
    }

    public void addBitmapRequest(BitmapRequest bitmapRequest){
        if (bitmapRequest!=null&&!mQueue.contains(bitmapRequest)){
            mQueue.add(bitmapRequest);
        }
    }
}
