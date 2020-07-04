package com.example.myglide.cache.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.example.myglide.cache.base.BaseCache;

public class DiskCache implements BaseCache {
    @Override
    public void put(Object key, Object value) {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int maxSize = maxMemory / 8;
        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public Object get() {
        return null;
    }

    @Override
    public void remove(Object key) {

    }
}
