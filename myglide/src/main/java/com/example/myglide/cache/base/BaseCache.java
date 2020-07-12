package com.example.myglide.cache.base;

import android.graphics.Bitmap;

public interface BaseCache {

    /**
     * 存入
     *
     * @param key
     * @param value
     */
    void put(String key, Bitmap value);

    /**
     * 获取
     *
     * @return
     */
    Bitmap get(String key);


    /**
     * 清除缓存
     *
     * @param key
     */
    void remove(String key);
}
